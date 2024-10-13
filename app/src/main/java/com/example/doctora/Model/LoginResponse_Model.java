    package com.example.doctora.Model;

    public class LoginResponse_Model {
        private String status;
        private String message;
        private Doctor doctor;

        public String getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public Doctor getDoctor() {
            return doctor;
        }

        public class Doctor {
            private String id;
            private String name;
            private String specialty;
            private String experience;
            private String location;
            private String photo;

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getSpecialty() {
                return specialty;
            }

            public String getExperience() {
                return experience;
            }

            public String getLocation() {
                return location;
            }

            public String getPhoto() {
                return photo;
            }
        }
    }


