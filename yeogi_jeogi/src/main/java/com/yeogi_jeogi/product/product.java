package com.yeogi_jeogi.product;

public class product {
	
    private Integer id;
    private Integer pNo;
    private Integer rId;
    private String title;
    private String sort;
    private String price;
    private String content;
    private String img;
    private Integer count;
    private String sdate;
    private String edate;
    private String udate;
    private Integer lpId;
    
    public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	private String time;
    private String local;
    private String img1;
    private String img2;
    
    public int getId() {
    	return id;
    }

    public void setId(int id) {
    	this.id = id;
    }
    
    public int getrId() {
    	return rId;
    }

    public void setrId(int rid) {
    	this.rId = rid;
    }
    
    
    public int getpNo() {
    	return pNo;
    }
    
    public void setpNo(int pNo) {
    	this.pNo = pNo;
    }
    
    public String getTitle() {
    	return title;
    }

    public void setTitle(String title) {
    	this.title = title;
    }
    
    public String getPrice() {
    	return price;
    }
    
    public void setPrice(String price) {
    	this.price = price;
    }
    
    public String getSort() {
    	return sort;
    }
    
    public void setSort(String sort) {
    	this.sort = sort;
    }
    
    public String getContent() {
    	return content;
    }
    
    public void setContent(String content) {
    	this.content = content;
    }
    
    public String getImg() {
    	return img;
    }
    
    public void setImg(String img) {
    	this.img = img;
    }
    
    public int getCount() {
    	return count;
    }
    
    public void setCount(int count) {
    	this.count = count;
    }
    
    public String getSdate() {
    	return sdate;
    }
    
    public void setSdate(String sdate) {
    	this.sdate = sdate;
    }
    
    public String getEdate() {
    	return edate;
    }
    
    public void setEdate(String edate) {
    	this.edate = edate;
    }
    
    public String getUdate() {
    	return udate;
    }
    
    public void setUdate(String udate) {
    	this.udate = udate;
    }
    
    public int getlpId() {
    	return lpId;
    }

    public void setlpId(int lpId) {
    	this.lpId = lpId;
    }

}
