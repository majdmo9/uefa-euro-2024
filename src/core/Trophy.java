package core;

import java.util.Date;

import utils.E_Trophy;

public class Trophy<T> implements Comparable<Trophy<T>> {
	private E_Trophy trophy;
	private T owner;
	private Date trophyWinningDate;

	public Trophy(E_Trophy trophy, T owner, Date trophyWinningDate) {
		this.trophy = trophy;
		this.owner = owner;
		this.trophyWinningDate = trophyWinningDate;
	}

	public E_Trophy getTrophy() {
		return trophy;
	}

	public void setTrophy(E_Trophy trophy) {
		this.trophy = trophy;
	}

	public T getOwner() {
		return owner;
	}

	public void setOwner(T owner) {
		this.owner = owner;
	}

	public Date getTrophyWinningDate() {
		return trophyWinningDate;
	}

	public void setTrophyWinningDate(Date trophyWinningDate) {
		this.trophyWinningDate = trophyWinningDate;
	}

	@Override
	public String toString() {
		return "Trophy [trophy=" + trophy + ", owner=" + owner + ", trophyWinningDate=" + trophyWinningDate + "]";
	}

	@Override
	public int compareTo(Trophy<T> o) {
		return this.getTrophyWinningDate().compareTo(o.getTrophyWinningDate());
	}
}
