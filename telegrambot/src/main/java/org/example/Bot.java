package org.example;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Bot extends TelegramLongPollingBot {

    private List<String> mensajes = new ArrayList<>();

    @Override
    public String getBotUsername() {
        return "P3tibio_bot";
    }

    @Override
    public String getBotToken() {
       return  "7118629351:AAFO4VrLAuKI8DwYOOpbSWv77VgvZ_zd8Xw";
    }

    @Override
    public void onUpdateReceived(Update update) {
        var usuario = update.getMessage().getFrom();
        var mensaje = update.getMessage().getText();
        var id = usuario.getId();
        mensajes.add(usuario+ " " + mensaje);
        System.out.println("id:" + usuario.getId()+"nombre:"+usuario.getFirstName()+" "+
        usuario.getLastName()+ "escribio" + mensaje);

        if(mensaje.contains("/desplegar")&& mensajes.size()>0){
            String Lista = "";
            for (String mensaje1 : mensajes) {
                Lista += mensaje1 + "\n";

            }
        }
    }

    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}

