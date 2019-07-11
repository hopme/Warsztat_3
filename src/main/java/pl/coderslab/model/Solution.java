package pl.coderslab.model;

public class Solution {

   private int id;
   private String created;
   private String updated;
   private String description;
   private int excerciseId;
   private int userId;

   public Solution() {
   }

   public Solution(String created, String updated, String description, int excerciseId, int userId) {
      this.created = created;
      this.updated = updated;
      this.description = description;
      this.excerciseId = excerciseId;
      this.userId = userId;
   }


   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getCreated() {
      return created;
   }

   public void setCreated(String created) {
      this.created = created;
   }

   public String getUpdated() {
      return updated;
   }

   public void setUpdated(String updated) {
      this.updated = updated;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public int getExcerciseId() {
      return excerciseId;
   }

   public void setExcerciseId(int excerciseId) {
      this.excerciseId = excerciseId;
   }

   public int getUserId() {
      return userId;
   }

   public void setUserId(int userId) {
      this.userId = userId;
   }

   @Override
   public String toString() {
      return "Solution{" +
              "id=" + id +
              ", created='" + created + '\'' +
              ", updated='" + updated + '\'' +
              ", description='" + description + '\'' +
              ", excerciseId=" + excerciseId +
              ", userId=" + userId +
              '}';
   }
}
