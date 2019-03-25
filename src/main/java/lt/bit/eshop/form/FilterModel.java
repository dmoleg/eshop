package lt.bit.eshop.form;

import org.springframework.data.domain.Sort;

public class FilterModel {
    private String name;

    private String sortBy = "name";

    private String sortDirection = "ASC";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }
}
