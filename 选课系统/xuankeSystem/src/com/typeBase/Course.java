package com.typeBase;

public class Course
{
	private String cname;

    private String chour;

    private String cdemand;

    private String ccontent;

    private String cpattern;

    private String cteach;

    private String cexcamine;

    public Course(String cname, String chour, String cdemand, String ccontent, String cpattern, String cteach, String cexcamine) 
    {
        this.cname = cname;
        this.chour = chour;
        this.cdemand = cdemand;
        this.ccontent = ccontent;
        this.cpattern = cpattern;
        this.cteach = cteach;
        this.cexcamine = cexcamine;
    }

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public String getChour()
	{
		return chour;
	}

	public void setChour(String chour)
	{
		this.chour = chour;
	}

	public String getCdemand()
	{
		return cdemand;
	}

	public void setCdemand(String cdemand)
	{
		this.cdemand = cdemand;
	}

	public String getCcontent()
	{
		return ccontent;
	}

	public void setCcontent(String ccontent)
	{
		this.ccontent = ccontent;
	}

	public String getCpattern()
	{
		return cpattern;
	}

	public void setCpattern(String cpattern)
	{
		this.cpattern = cpattern;
	}

	public String getCteach()
	{
		return cteach;
	}

	public void setCteach(String cteach)
	{
		this.cteach = cteach;
	}

	public String getCexcamine()
	{
		return cexcamine;
	}

	public void setCexcamine(String cexcamine)
	{
		this.cexcamine = cexcamine;
	}
}
