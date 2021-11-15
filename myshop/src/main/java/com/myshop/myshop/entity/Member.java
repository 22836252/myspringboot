package com.myshop.myshop.entity;

import javax.persistence.*;
@Entity
@Table
public class Member {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id;

        @Column
        String email;

        @Column
        String password;

        @Column
        String firstName;

        @Column
        String lastName;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

