const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const cors = require('cors');
const bcrypt = require('bcrypt');
require('dotenv').config();

const app = express();
const PORT = process.env.PORT || 5000;

// Middleware
app.use(cors());
app.use(bodyParser.json());

// MongoDB Connection
mongoose.connect(process.env.MONGO_URI)
    .then(() => console.log('MongoDB Connected'))
    .catch(err => console.error(err));

// User Schema
const UserSchema = new mongoose.Schema({
    Userid: { type: String, required: true, unique: true },
    PasswordHash: { type: String, required: true },
});

const User = mongoose.model('User', UserSchema, 'Users');

// ClothingItem Schema (example from your existing code)
const ClothingItemSchema = new mongoose.Schema({
    ClothingID: String,
    UserID: String,
    ItemName: String,
    ItemType: String,
    Category: String,
    Color: String,
    Material: String,
    Size: String,
    Brand: String,
    Season: String,
    Occasion: String,
    ImageURL: String,
    ModelURL: String,
});

const ClothingItems = mongoose.model('ClothingItems', ClothingItemSchema, 'ClothingItems');

// API Endpoints: User Registration
app.post('/register', async (req, res) => {
    console.log("Request body:", req.body);

    const { Userid, PasswordHash } = req.body;

    if (!Userid || !PasswordHash) {
        return res.status(400).json({ message: 'Missing Userid or PasswordHash' });
    }

    try {
        const existingUser = await User.findOne({ Userid });
        if (existingUser) {
            return res.status(400).json({ message: 'Userid already exists' });
        }

        const hashedPassword = await bcrypt.hash(PasswordHash, 10);
        const newUser = new User({ Userid, PasswordHash: hashedPassword });
        await newUser.save();
        res.status(201).json({ message: 'User registered successfully' });
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// API Endpoints: User Login
app.post('/login', async (req, res) => {
    const { Userid, PasswordHash } = req.body;

    if (!Userid || !PasswordHash) {
        return res.status(400).json({ message: 'Missing Userid or PasswordHash' });
    }

    try {
        const user = await User.findOne({ Userid });
        if (!user) {
            return res.status(401).json({ message: 'Invalid credentials' });
        }

        const isPasswordValid = await bcrypt.compare(PasswordHash, user.PasswordHash);
        if (!isPasswordValid) {
            return res.status(401).json({ message: 'Invalid credentials' });
        }

        res.status(200).json({ message: 'Login successful' });
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// API Endpoints: Get all clothing items
app.get('/ClothingItems', async (req, res) => {
    try {
        const items = await ClothingItems.find();
        res.status(200).json(items);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// API Endpoints: Add a new clothing item
app.post('/ClothingItems', async (req, res) => {
    try {
        const newClothingItem = new ClothingItems(req.body);
        await newClothingItem.save();
        res.status(201).json(newClothingItem);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// API Endpoints: Get clothing item by ID
app.get('/ClothingItems/:id', async (req, res) => {
    try {
        const clothingItem = await ClothingItems.findById(req.params.id);
        if (!clothingItem) {
            return res.status(404).json({ message: 'Clothing item not found' });
        }
        res.status(200).json(clothingItem);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// API Endpoints: Update clothing item by ID
app.put('/ClothingItems/:id', async (req, res) => {
    try {
        const updatedClothingItem = await ClothingItems.findByIdAndUpdate(req.params.id, req.body, { new: true });
        if (!updatedClothingItem) {
            return res.status(404).json({ message: 'Clothing item not found' });
        }
        res.status(200).json(updatedClothingItem);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// API Endpoints: Delete clothing item by ID
app.delete('/ClothingItems/:id', async (req, res) => {
    try {
        const deletedClothingItem = await ClothingItems.findByIdAndDelete(req.params.id);
        if (!deletedClothingItem) {
            return res.status(404).json({ message: 'Clothing item not found' });
        }
        res.status(200).json({ message: 'Clothing item deleted' });
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Start Server
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
