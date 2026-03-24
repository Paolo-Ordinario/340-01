package CRUD.API.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/sender/{senderId}")
    public List<Message> getMessagesBySender(@PathVariable Long senderId) {
        return messageService.getMessagesBySender(senderId);
    }

    @GetMapping("/receiver/{receiverId}")
    public List<Message> getMessagesByReceiver(@PathVariable Long receiverId) {
        return messageService.getMessagesByReceiver(receiverId);
    }

    @GetMapping("/conversation/{senderId}/{receiverId}")
    public List<Message> getConversation(@PathVariable Long senderId, @PathVariable Long receiverId) {
        return messageService.getConversation(senderId, receiverId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMessageById(@PathVariable Long id) {
        Optional<Message> message = messageService.getMessageById(id);
        if (message.isPresent()) {
            return ResponseEntity.ok(message.get());
        } else {
            return ResponseEntity.status(404).body("Message with ID " + id + " not found");
        }
    }

    @PostMapping
    public ResponseEntity<Object> sendMessage(@RequestBody Message message) {
        Message saved = messageService.sendMessage(message);
        return ResponseEntity.status(201).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMessage(@PathVariable Long id) {
        Optional<Message> existing = messageService.getMessageById(id);
        if (existing.isPresent()) {
            messageService.deleteMessage(id);
            return ResponseEntity.ok("Message deleted.");
        } else {
            return ResponseEntity.status(404).body("Message not found.");
        }
    }
}