package by.bstu.po15.ats.web.entity;

public class SelectList
{
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    private String value;
    public String getValue() {
        return value;
    }


    private boolean selected;
    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public SelectList(Long id, String value, boolean selected)
    {   this.id=id;
        this.value = value;
        this.selected = selected;

    }

}
