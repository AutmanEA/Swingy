package model;

public sealed interface MoveResult
permits MoveResult.Nothing, MoveResult.Fight, MoveResult.Victory
{
	record Nothing() implements MoveResult {}
	record Fight() implements MoveResult {}
	record Victory() implements MoveResult {}

}
