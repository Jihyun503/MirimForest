package mirim_forest;

//DB 테이블에서 각각의 값을 가져오기 위한 메소드
public class User {
	public String name;
	public int gender;
	public long fitime;
	public long stime;
	public int desk;
	public int bed;
	public int chair;
	public int closet;
	public int sofa;
	public int potato;
	public int deskX;
	public int deskY;
	public int bedX;
	public int bedY;
	public int chairX;
	public int chairY;
	public int closetX;
	public int closetY;
	public int sofaX;
	public int sofaY;
	public int potatoX;
	public int potatoY;
	public int point;
	public int gift;
	public int clothes;
	
	public User(String name, int gender, long fitime, long stime, int point, int desk, int bed, int chair, int closet, int sofa, int potato, int deskX,
	int deskY, int bedX, int bedY, int chairX, int chairY, int closetX, int closetY, int sofaX, int sofaY, int potatoX, int potatoY, int gift, int clothes) 
	{
		this.name = name;
		this.gender = gender;
		this.fitime = fitime;
		this.stime = stime;
		this.point = point;
		this.desk = desk;
		this.chair = chair;
		this.closet = closet;
		this.sofa = sofa;
		this.potato = potato;
		this.deskX = deskX;
		this.deskY = deskY;
		this.bedX = bedX;
		this.bedY = bedY;
		this.chairX = chairX;
		this.chairY = chairY;
		this.closetX = closetX;
		this.closetY = closetY;
		this.sofaX = sofaX;
		this.sofaY = sofaY;
		this.potatoX = potatoX;
		this.potatoY = potatoY;
		this.gift = gift;
		this.clothes = clothes;
	}

	// 필요한 메서드 포함 가능 (완전히 일반적인 객체처럼 사용 가능이 장점)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getGender() {
		return gender;
	}
	
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public long getfitime() {
		return fitime;
	}
	
	public void setFitime(long fitime) {
		this.fitime = fitime;
	}
	
	public long getstime() {
		return stime;
	}
	
	public void setStime(long stime) {
		this.stime = stime;
	}

	public int getdesk() {
		return desk;
	}
	
	public void setDesk(int desk) {
		this.desk = desk;
	}
	
	public int getbed() {
		return bed;
	}
	
	public void setBed(int bed) {
		this.bed = bed;
	}

	public int getchair() {
		return chair;
	}
	
	public void setChair(int chair) {
		this.chair = chair;
	}
	
	public int getcloset() {
		return closet;
	}
	
	public void setCloset(int closet) {
		this.closet = closet;
	}
	
	public int getsofa() {
		return sofa;
	}
	
	public void setSofa(int sofa) {
		this.sofa = sofa;
	}
	
	public int getpotato() {
		return potato;
	}
	
	public void setPotato(int potato) {
		this.potato = potato;
	}
	
	public int getdeskX() {
		return deskX;
	}
	
	public void setDeskX(int deskX) {
		this.deskX = deskX;
	}
	
	public int getdeskY() {
		return deskY;
	}
	
	public void setDeskY(int deskY) {
		this.deskY = deskY;
	}
	
	public int getbedX() {
		return bedX;
	}
	
	public void setBedX(int bedX) {
		this.bedX = bedX;
	}
	
	public int getbedY() {
		return bedY;
	}
	
	public void setBedY(int bedY) {
		this.bedY = bedY;
	}
	
	public int getchairX() {
		return chairX;
	}
	
	public void setChairX(int chairX) {
		this.chairX = chairX;
	}
	
	public int getchairY() {
		return chairY;
	}
	
	public void setChairY(int chairY) {
		this.chairY = chairY;
	}
	
	public int getclosetX() {
		return closetX;
	}
	
	public void setClosetX(int closetX) {
		this.closetX = closetX;
	}
	
	public int getclosetY() {
		return closetY;
	}
	
	public void setClosetY(int closetY) {
		this.closetY = closetY;
	}
	
	public int getsofaX() {
		return sofaX;
	}
	
	public void setSofaX(int sofaX) {
		this.sofaX = sofaX;
	}
	
	public int getsofaY() {
		return closetY;
	}
	
	public void setSofaY(int sofaY) {
		this.sofaY = sofaY;
	}
	
	public int getpotatoX() {
		return potatoX;
	}
	
	public void setPotatoX(int potatoX) {
		this.potatoX = potatoX;
	}
	
	public int getpotatoY() {
		return potatoY;
	}
	
	public void setPotatoY(int potatoY) {
		this.potatoY = potatoY;
	}
	
	public int getpoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	public int getgift() {
		return gift;
	}
	
	public void setGift(int gift) {
		this.gift = gift;
	}
	
	public int getclothes() {
		return clothes;
	}
	
	public void setClothes(int clothes) {
		this.clothes = clothes;
	}
}