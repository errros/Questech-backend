package com.elab.elearning.elearning.model;


import com.elab.elearning.elearning.model.Promo;
import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class GroupId implements Serializable {

    private Promo promo;

    private Long id;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupId groupId = (GroupId) o;

        if (promo != groupId.promo) return false;
        return id.equals(groupId.id);
    }

    @Override
    public int hashCode() {
        int result = promo.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GroupId{" +
                "promo=" + promo +
                ", id=" + id +
                '}';
    }
}
