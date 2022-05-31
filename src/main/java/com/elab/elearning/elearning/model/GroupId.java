package com.elab.elearning.elearning.model;


import com.elab.elearning.elearning.model.Promo;
import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class GroupId implements Serializable {
private Promo promo;
private Long id;




}
