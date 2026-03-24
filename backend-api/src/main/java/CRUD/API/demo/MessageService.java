package CRUD.API.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    public List<Message> getMessagesBySender(Long senderId) {
        return messageRepository.findBySenderId(senderId);
    }

    public List<Message> getMessagesByReceiver(Long receiverId) {
        return messageRepository.findByReceiverId(receiverId);
    }

    public List<Message> getConversation(Long senderId, Long receiverId) {
        return messageRepository.findBySenderIdAndReceiverId(senderId, receiverId);
    }

    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}