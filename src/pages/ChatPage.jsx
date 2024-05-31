import React, { useState, useEffect } from 'react';
import {
    TextField,
    IconButton,
    List,
    ListItem,
    ListItemText,
    ListItemAvatar,
    Avatar,
    Typography,
    Button,
} from '@material-ui/core';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import SectionTitle from "../components/SectionTitle";

const ChatPage = () => {
    const [messages, setMessages] = useState([]);
    const [message, setMessage] = useState('');
    const [nickname, setNickname] = useState('');
    const [stompClient, setStompClient] = useState(null);

    useEffect(() => {
        const socket = new SockJS('http://localhost:5173/ws');
        const client = Stomp.over(socket);

        client.connect({}, () => {
            client.subscribe('/topic/messages', (message) => {
                const receivedMessage = JSON.parse(message.body);
                setMessages((prevMessages) => [...prevMessages, receivedMessage]);
            });
        });

        setStompClient(client);

        return () => {
            client.disconnect();
        };
    }, []);

    const handleNicknameChange = (event) => {
        setNickname(event.target.value);
    };

    const handleMessageChange = (event) => {
        setMessage(event.target.value);
    };

    const sendMessage = () => {
        if (message.trim()) {
            const chatMessage = {
                nickname,
                content: message,
            };

            // Add the new message to the state immediately
            setMessages(prevMessages => [...prevMessages, chatMessage]);

            // Send the message through stompClient
            stompClient.send('/app/chat', {}, JSON.stringify(chatMessage));
            setMessage('');
        }
    };


    return (
        <div className="container mx-auto px-4 py-8">
            <SectionTitle title="Chat" path="Home | Chat" />
            <div className="space-y-4">
                <List>
                    {messages.map((msg, index) => (
                        <ListItem key={index}>
                            <ListItemAvatar>
                                <Avatar>{msg.nickname.charAt(0)}</Avatar>
                            </ListItemAvatar>
                            <ListItemText
                                primary={
                                    <Typography variant="subtitle1" className="luxury-text">{msg.nickname}</Typography>
                                }
                                secondary={<Typography variant="body2" className="luxury-text">{msg.content}</Typography>}
                            />
                        </ListItem>
                    ))}
                </List>

                <div className="flex items-center space-x-4 my-4">
                    <TextField
                        placeholder="Enter your nickname"
                        value={nickname}
                        onChange={handleNicknameChange}
                        autoFocus
                        className="bg-white text-black"
                    />
                    <TextField
                        placeholder="Type a message"
                        value={message}
                        onChange={handleMessageChange}
                        fullWidth
                        className="bg-white text-black"
                    />
                    <Button variant="contained" color="primary" onClick={sendMessage} disabled={!message.trim()}>
                        Send
                    </Button>
                </div>

            </div>
        </div>
    );
};

export default ChatPage;
