package entity;

/**
 * Thông tin về ca trực
 * 
 * @author Nguyen Tu
 */
public class Shift {
	private String date;
	private int shiftNumber;
	private int typeWork;

	/**
	 * Constructor
	 * 
	 * @param date
	 *            Thứ 2, thứ 3, ... hoặc chủ nhật và ngày, tháng, năm
	 * @param shiftNumber
	 *            ca trực thứ mấy
	 * @param typeWork
	 *            <b>1:</b> là đăng ký làm việc, <b>2:</b> là đăng ký vắng,
	 *            <b>0:</b> là không làm việc
	 */
	public Shift(String date, int shiftNumber, int typeWork) {
		this.date = date;
		this.shiftNumber = shiftNumber;
		this.typeWork = typeWork;
	}

	public int getStartTime() {
		switch (shiftNumber) {
		case 1:
			return 7;
		case 2:
			return 13;
		case 3:
			return 18;
		default:
			return -1;
		}
	}

	public int getStopTime() {
		switch (shiftNumber) {
		case 1:
			return 11;
		case 2:
			return 17;
		case 3:
			return 22;
		default:
			return -1;
		}
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getShiftNumber() {
		return shiftNumber;
	}

	public void setShiftNumber(int shiftNumber) {
		this.shiftNumber = shiftNumber;
	}

	public int getTypeWork() {
		return typeWork;
	}

	public void setTypeWork(int typeWork) {
		this.typeWork = typeWork;
	}
}
