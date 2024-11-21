const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const cors = require('cors');
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

// ClothingItem Schema
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

const ClothingItems = mongoose.model('ClothingItems', ClothingItemSchema,'ClothingItems');

// API Endpoints

// Get all clothing items
app.get('/ClothingItems', async (req, res) => {
    try {
        const items = await ClothingItems.find();
        res.status(200).json(items);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Add a new clothing item
app.post('/ClothingItems', async (req, res) => {
    console.log('Received Data:', req.body);
    try {
        const newClothingItem = new ClothingItems(req.body);
        await newClothingItem.save();
        console.log('Saved Item:', newClothingItem);
        res.status(201).json(newClothingItem);
    } catch (err) {
        console.error('Error:', err);
        res.status(500).json({ error: err.message });
    }
});

// Get clothing item by ID
app.get('/ClothingItems/:id', async (req, res) => {
    try {
        const ClothingItem = await ClothingItems.findById(req.params.id);
        if (!ClothingItem) return res.status(404).json({ message: 'Clothing item not found' });
        res.status(200).json(ClothingItem);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Update a clothing item by ID
app.put('/ClothingItems/:id', async (req, res) => {
    try {
        const updatedClothingItem = await ClothingItems.findByIdAndUpdate(req.params.id, req.body, { new: true });
        if (!updatedClothingItem) return res.status(404).json({ message: 'Clothing item not found' });
        res.status(200).json(updatedClothingItem);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Delete a clothing item by ID
app.delete('/ClothingItems/:id', async (req, res) => {
    try {
        const deletedClothingItem = await ClothingItems.findByIdAndDelete(req.params.id);
        if (!deletedClothingItem) return res.status(404).json({ message: 'Clothing item not found' });
        res.status(200).json({ message: 'Clothing item deleted' });
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Start Server
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
