package entities;

import java.util.Date;

public class BaseClass {
    public Date createdAt;
    public Date lastModifiedAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Date lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
