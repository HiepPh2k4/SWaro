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

const ClothingItem = mongoose.model('ClothingItem', ClothingItemSchema);

// API Endpoints

// Get all clothing items
app.get('/clothingitems', async (req, res) => {
    try {
        const clothingItems = await ClothingItem.find();
        res.status(200).json(clothingItems);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Add a new clothing item
app.post('/clothingitems', async (req, res) => {
    try {
        const newClothingItem = new ClothingItem(req.body);
        await newClothingItem.save();
        res.status(201).json(newClothingItem);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Get clothing item by ID
app.get('/clothingitems/:id', async (req, res) => {
    try {
        const clothingItem = await ClothingItem.findById(req.params.id);
        if (!clothingItem) return res.status(404).json({ message: 'Clothing item not found' });
        res.status(200).json(clothingItem);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Update a clothing item by ID
app.put('/clothingitems/:id', async (req, res) => {
    try {
        const updatedClothingItem = await ClothingItem.findByIdAndUpdate(req.params.id, req.body, { new: true });
        if (!updatedClothingItem) return res.status(404).json({ message: 'Clothing item not found' });
        res.status(200).json(updatedClothingItem);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Delete a clothing item by ID
app.delete('/clothingitems/:id', async (req, res) => {
    try {
        const deletedClothingItem = await ClothingItem.findByIdAndDelete(req.params.id);
        if (!deletedClothingItem) return res.status(404).json({ message: 'Clothing item not found' });
        res.status(200).json({ message: 'Clothing item deleted' });
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Start Server
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
