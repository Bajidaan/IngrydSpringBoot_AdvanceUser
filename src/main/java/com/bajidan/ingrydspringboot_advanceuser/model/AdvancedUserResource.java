package com.bajidan.ingrydspringboot_advanceuser.model;

import lombok.Data;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Data
public class AdvancedUserResource extends RepresentationModel<AdvancedUserResource> {
    private AdvancedUser advancedUser;
}
