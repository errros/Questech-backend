package com.elab.elearning.elearning.entity;

public class File {
        private String name;
        private String url;
        public File(String name, String url) {
            this.name = name;
            this.url = url;
        }
        public String getName() {
            return this.name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getUrl() {
            return this.url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
    }

