from config import get_database

# Kết nối đến cơ sở dữ liệu
db = get_database()

# Lấy các collection cụ thể từ cơ sở dữ liệu
user_collection = db['Users']
clothing_collection = db['ClothingItems']

# Thêm người dùng mới vào collection Users
def add_user(user_data):
    user_collection.insert_one(user_data)
    print(f"Đã thêm người dùng với ID: {user_data['UserID']}")

# Lấy thông tin người dùng theo UserID
def get_user(user_id):
    user = user_collection.find_one({"UserID": user_id})
    if user:
        print("Thông tin người dùng:", user)
    else:
        print(f"Không tìm thấy người dùng với ID: {user_id}")
    return user

# Thêm món đồ vào collection ClothingItems
def add_clothing_item(item_data):
    clothing_collection.insert_one(item_data)
    print(f"Đã thêm món đồ với ID: {item_data['ClothingID']}")

# Lấy danh sách quần áo của một người dùng
def get_user_clothing(user_id):
    clothing_items = clothing_collection.find({"UserID": user_id})
    print(f"Các món đồ trong tủ quần áo của người dùng {user_id}:")
    return list(clothing_items)

# Cập nhật thông tin người dùng
def update_user(user_id, updated_data):
    result = user_collection.update_one({"UserID": user_id}, {"$set": updated_data})
    if result.matched_count:
        print(f"Đã cập nhật thông tin người dùng với ID: {user_id}")
    else:
        print(f"Không tìm thấy người dùng với ID: {user_id}")

# Xóa người dùng
def delete_user(user_id):
    result = user_collection.delete_one({"UserID": user_id})
    if result.deleted_count:
        print(f"Đã xóa người dùng với ID: {user_id}")
    else:
        print(f"Không tìm thấy người dùng với ID: {user_id}")

# Cập nhật thông tin món đồ quần áo
def update_clothing_item(clothing_id, updated_data):
    result = clothing_collection.update_one({"ClothingID": clothing_id}, {"$set": updated_data})
    if result.matched_count:
        print(f"Đã cập nhật món đồ với ID: {clothing_id}")
    else:
        print(f"Không tìm thấy món đồ với ID: {clothing_id}")

# Xóa món đồ quần áo
def delete_clothing_item(clothing_id):
    result = clothing_collection.delete_one({"ClothingID": clothing_id})
    if result.deleted_count:
        print(f"Đã xóa món đồ với ID: {clothing_id}")
    else:
        print(f"Không tìm thấy món đồ với ID: {clothing_id}")

# Gợi ý outfit ngẫu nhiên cho người dùng
import random

def suggest_random_outfit(user_id):
    clothing_items = list(clothing_collection.find({"UserID": user_id}))
    if len(clothing_items) < 3:
        print(f"Người dùng {user_id} chưa có đủ món đồ để tạo outfit.")
        return []
    
    # Gợi ý 3 món đồ ngẫu nhiên từ danh sách quần áo
    outfit = random.sample(clothing_items, 3)
    print("Gợi ý outfit ngẫu nhiên cho người dùng:", user_id)
    return outfit
