package board;

public class Board {
	private String boardType;
	private int boardNum;
	private String boardTitle;
	private String boardComment;
	private String creator;
	private String creatorTime;
	private String modifier;
	private String modifiedTime;
	private String fileRoot;
	
	public Board() {
		
	}
	
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardComment() {
		return boardComment;
	}
	public void setBoardComment(String boardComment) {
		this.boardComment = boardComment;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatorTime() {
		return creatorTime;
	}
	public void setCreatorTime(String creatorTime) {
		this.creatorTime = creatorTime;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getFileRoot() {
		return fileRoot;
	}
	public void setFileRoot(String fileRoot) {
		this.fileRoot = fileRoot;
	}
	
	@Override
	public String toString() {
		return "Board [boardType=" + boardType + ", boardNum=" + boardNum + ", boardTitle=" + boardTitle
				+ ", boardComment=" + boardComment + ", creator=" + creator + ", creatorTime=" + creatorTime
				+ ", modifier=" + modifier + ", modifiedTime=" + modifiedTime + ", fileRoot=" + fileRoot + "]";
	}
	// 업데이트 보드 사용하기 위한 동작
	public void updateBoard(Board upBean) {
		// TODO Auto-generated method stub
		
	}
}