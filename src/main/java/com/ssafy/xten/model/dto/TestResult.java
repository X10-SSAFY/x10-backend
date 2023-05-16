package com.ssafy.xten.model.dto;

public class TestResult {
	private int testResultSeq;
	private int userSeq;
	private String testDate;

	public TestResult() {
	}

	public TestResult(int testResultSeq, int userSeq, String testDate) {
		super();
		this.testResultSeq = testResultSeq;
		this.userSeq = userSeq;
		this.testDate = testDate;
	}

	public int getTestResultSeq() {
		return testResultSeq;
	}

	public void setTestResultSeq(int testResultSeq) {
		this.testResultSeq = testResultSeq;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

}
