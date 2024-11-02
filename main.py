from db_operations import (
    add_user, get_user, add_clothing_item, get_user_clothing, 
    update_user, delete_user, update_clothing_item, delete_clothing_item, 
    suggest_random_outfit
)

# Dữ liệu mẫu người dùng
user_data = {
    "UserID": "u001",
    "FullName": "Alice Nguyen",
    "Email": "alice.nguyen@example.com",
    "PasswordHash": "hashed_password",
    "Gender": "Female",
    "BodyMeasurements": {
        "Height": 165,
        "Weight": 55,
        "Chest": 85,
        "Waist": 60,
        "Hip": 90
    },
    "AvatarURL": "http://example.com/avatar.jpg",
    "3DModelURL": "http://example.com/model.glb"
}

# Thêm người dùng mới
add_user(user_data)

# Lấy thông tin người dùng
get_user("u001")

# Cập nhật thông tin người dùng
updated_user_data = {"FullName": "Alice Nguyen Updated"}
update_user("u001", updated_user_data)

# Xóa người dùng
# delete_user("u001")

# Dữ liệu mẫu món đồ quần áo
clothing_data = {
    "ClothingID": "c001",
    "UserID": "u001",
    "ItemName": "Red T-Shirt",
    "ItemType": "Top",
    "Category": "Casual",
    "Color": "Red",
    "Material": "Cotton",
    "Size": "M",
    "Brand": "Nike",
    "Season": "Summer",
    "Occasion": "Everyday",
    "ImageURL": "http://example.com/clothing.jpg",
    "3DModelURL": "http://example.com/clothing.glb"
}

# Thêm một món đồ quần áo mới
add_clothing_item(clothing_data)

# Lấy danh sách quần áo của người dùng
get_user_clothing("u001")

# Cập nhật món đồ quần áo
updated_clothing_data = {"Size": "L"}
update_clothing_item("c001", updated_clothing_data)

# Xóa món đồ quần áo
# delete_clothing_item("c001")

# Gợi ý outfit ngẫu nhiên cho người dùng
suggest_random_outfit("u001")
