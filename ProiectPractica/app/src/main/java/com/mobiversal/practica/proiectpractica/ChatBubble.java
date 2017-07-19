package com.mobiversal.practica.proiectpractica;

/**
 * Created by user on 7/17/2017.
 */

public class ChatBubble {

        private String content;
        private String name;
        private boolean myMessage;

        public ChatBubble(String content, String name, boolean myMessage) {
            this.name = name;
            this.content = content;
            this.myMessage = myMessage;
        }

        public String getContent() {
            return content;
        }

        public boolean myMessage() {
            return myMessage;
        }

        public String getName(){
            return name;
        }

}
