package service;

public class UploadDTO {
	private String filename;
	private boolean	isSuccess;
	private String fullfilename;
	
	
	
	public String getFullfilename() {
		return fullfilename;
	}
	public void setFullfilename(String fullfilename) {
		this.fullfilename = fullfilename;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
}
