package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;

@Entity
public class Person {

    public static class Builder implements
            org.apache.commons.lang3.builder.Builder<Person> {
        private String email;

        public Builder(String email) {
            this.email = email;
        }

        @Override
        public Person build() {
            return new Person(this);
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }
    }

    @Required
    @Email
    @Id
    private String email;
    
    public Person() {
    
    }

    private Person(Builder builder) {
        setEmail(builder.email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}