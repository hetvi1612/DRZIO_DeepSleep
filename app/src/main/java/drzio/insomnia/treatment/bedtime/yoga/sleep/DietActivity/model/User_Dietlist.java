package drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model;


import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.ListItem;

public class User_Dietlist implements ListItem {

    public String date;
    public String id;
    public String name;
    public String description;
    public String image;
    public String is_active;
    public String user_type;
    public String category_id;
    public boolean isCheckd;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    @Override
    public int getItemType() {
        return ListItem.TYPE_ITEM;
    }

    public boolean isCheckd() {
        return isCheckd;
    }

    public void setCheckd(boolean checkd) {
        isCheckd = checkd;
    }
}
