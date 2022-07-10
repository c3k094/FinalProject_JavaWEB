package PETVET.bg.petvet.model.view;

public class OwnerDropDownView {
    private String name;
    private Long id;

    public OwnerDropDownView() {
    }

    public String getName() {
        return name;
    }

    public OwnerDropDownView setName(String name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OwnerDropDownView setId(Long id) {
        this.id = id;
        return this;
    }
}
