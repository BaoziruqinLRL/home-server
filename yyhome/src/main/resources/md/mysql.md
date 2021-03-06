[toc]

## Mysql

### Innodb事务
	ACID四项通过锁和Redo Log、Undo Log来实现。
- A（Atomicity原子性） C（Consistency一致性）。
	1. 通过Undo Log实现，实际上就是一个操作记录的反向记录日志，例如做了insert，则记录一条delete语句；做了update则记录一条原来数据的update。
	2. Undo Log本质是记录了事务开始前数据的原始状态，在事务提交前将Undo Log持久化到磁盘。
	3. Undo Log是逻辑日志，记录的是逻辑操作，就是sql语句
- D（Duration持久性）。
	
	1. 通过Redo Log实现，Redo Log本质是记录了事务过程中修改的数据的最新记录，在事务提交前会记录到磁盘，用于持久化
- I（Isolation隔离性）。通过Mysql的锁实现。
	2. Redo Log是物理日志，记录的是对每个页的修改，个人理解就是记录数据是什么
* 锁的类型分为
	
	3. 行级锁。分为读锁（共享锁 S Lock）和写锁（排它锁 X Lock）。
	
	4. 表级锁。分为意向读锁（共享锁 IS Lock）和意向写锁（共享锁 IX Lock）。
	    在mysql要获取S或X Lock前，会先申请IS 或 IX Lock，也就是申请表级锁，但表级锁只会阻塞住那些想要对全表进行操作的sql语句，如果事务A和事务B分别只处理同一个表的不同行数据，那么他们的先后加表级锁不会被阻塞。如果事务C要执行全表操作，那么在事务A或事务B之后执行，事务C就会被阻塞。
	    锁的算法分为
	
	5. Record Lock。单行记录锁。只锁一行记录
	
	6. Gap Lock 间隙锁。锁一个范围。
	
	7. Next-Key Lock。结合单行锁和间隙锁的组合。锁一个范围+一行记录，主要为了解决RR（Repeatable Read可重复读）隔离级别下的幻读，因此只有在RR级别下Sql走的是非唯一索引或主键索引才会使用Next-Key Lock。锁住的范围是例如三个数据 10、20、30，那么对20加间隙锁，锁住的范围是[10，30)，包含前一条数据但不包含后一条数据，在此范围内不能插入数据，但能更新除了20的其它数据，因为Next-Key不仅加了间隙锁，还加了行锁。
	
* 事务并发问题
	
	8. 脏读（读到了不确定的数据）。一个事务读到了另一个事务中未提交的数据
	
	9. 幻读（发生幻觉读取）。一个事务读到了另一个事务提交的insert或delete数据，多是指事务中作范围搜索或搜索若干行之后，在此范围内由于另一个事务插入了数据，导致第二次查询得到和第一次不同的结果。因此幻读需要加间隙锁来解决
	
	10. 不可重复读（虚读、虚假的读取）。一个事务读到了另一个事务中提交的update数据，指事务中两次读取同样的数据，因为另一个事务对这个数据做了更新，导致两次读取结果不一样。不可重复读只需要加行锁
* 事务隔离级别（从高到低）
	
	11. 串行执行。所有事务串行执行，最安全但效率最低
	
	12. 可重复读（Repeatable Read）。按照相同的查询条件多次查询，不会受其它事务的更新操作影响，也就是解决了不可重复读问题。而幻读问题通过Next-key Lock算法解决。不会有脏读、幻读和不可重复读的问题
	
	13. 已提交读（Read Commited）。每次查询都是读取当前数据的最新快照，也就是可以读取其它事务已经提交的数据。会有幻读和不可重复读问题
	
	14. 未提交读。不做任何限制，会有脏读、幻读、不可重复读问题

### 慢查询优化
- 检查like条件，是否有前置%查询
- 检查索引条件，最左原则匹配索引
- 检查limit分页语句，如果有分页可以使用索引覆盖写法，先通过索引查询一些关键列，再根据与关键列的关联查询得到结果
- 设置中间表查询，如果频繁查询大量关联数据，可以将常用查询数据组合成中间表进行查询
- 分解多表关联查询，分解为多次小的关联查询，剩下的逻辑在应用中处理