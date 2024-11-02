from pymongo.mongo_client import MongoClient
from pymongo.server_api import ServerApi

# Thay thế <username>, <password>, và <cluster> bằng thông tin thật của bạn
uri = "mongodb+srv://hola2k4:lvhl2004@swaro.hsyrx.mongodb.net/?retryWrites=true&w=majority&appName=SWaro"  # Nếu bạn đang sử dụng MongoDB cục bộ (local)

# Hàm kết nối đến MongoDB
def get_database():
    # Tạo client và kết nối đến MongoDB
    client = MongoClient(uri, server_api=ServerApi('1'))
    
    try:
        # Gửi lệnh ping để xác nhận kết nối
        client.admin.command('ping')
        print("Pinged your deployment. You successfully connected to MongoDB!")
    except Exception as e:
        print(f"Error: {e}")
    
    # Trả về đối tượng cơ sở dữ liệu
    return client['SmartWardrobeDB']
