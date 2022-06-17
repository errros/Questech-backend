package com.elab.elearning.elearning.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.time.DayOfWeek;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Embeddable
public class SessionId implements Serializable {
      private DayOfWeek day;
      private TimeOfDay time;
      @Embedded
      private GroupId groupId;


      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SessionId sessionId = (SessionId) o;

            if (day != sessionId.day) return false;
            if (time != sessionId.time) return false;
            return groupId.equals(sessionId.groupId);
      }

      @Override
      public int hashCode() {
            int result = day.hashCode();
            result = 31 * result + time.hashCode();
            result = 31 * result + groupId.hashCode();
            return result;
      }

      @Override
      public String toString() {
            return "SessionId{" +
                    "day=" + day +
                    ", time=" + time +
                    ", groupId=" + groupId +
                    '}';
      }
}
