# 文件结构:
# /
# ├── main.py           # 主程序入口
# ├── models/           # 数据模型
# │   ├── __init__.py
# │   ├── user.py       # 用户模型
# │   └── product.py    # 产品模型
# ├── services/         # 业务逻辑
# │   ├── __init__.py
# │   ├── auth_service.py    # 认证服务
# │   └── product_service.py # 产品服务
# ├── utils/            # 工具函数
# │   ├── __init__.py
# │   └── helpers.py    # 辅助函数
# └── config.py         # 配置文件

# main.py
from models.user import User
from models.product import Product
from services.auth_service import authenticate_user, register_user
from services.product_service import get_products, add_product
import config

def main():
    print(f"应用启动，当前环境: {config.ENVIRONMENT}")
    
    # 注册新用户
    new_user = register_user("user1", "password", "user1@example.com")
    print(f"注册用户: {new_user.username}")
    
    # 认证用户
    user = authenticate_user("user1", "password")
    if user:
        print(f"认证成功: {user.username}")
    
    # 添加产品
    product = add_product("手机", 3999.99, 10)
    print(f"添加产品: {product.name}, 价格: {product.price}")
    
    # 获取产品列表
    products = get_products()
    print(f"产品数量: {len(products)}")

if __name__ == "__main__":
    main()

# models/user.py
class User:
    def __init__(self, username, password, email):
        self.id = None  # 数据库会自动生成
        self.username = username
        self.password = password  # 问题：密码未加密存储
        self.email = email
        self.is_active = True
        self.created_at = None
    
    def to_dict(self):
        return {
            "id": self.id,
            "username": self.username,
            # 问题：返回了密码
            "password": self.password,
            "email": self.email,
            "is_active": self.is_active,
            "created_at": self.created_at
        }

# models/product.py
class Product:
    def __init__(self, name, price, stock_count):
        self.id = None  # 数据库会自动生成
        self.name = name
        self.price = price
        self.stock_count = stock_count
        if price < 0:  # 问题：应该是 price <= 0
            self.price = 0
    
    def is_in_stock(self):
        # 问题：应该是 return self.stock_count > 0
        return self.stock_count >= 0
    
    def apply_discount(self, discount_percent):
        if not isinstance(discount_percent, (int, float)):
            return False
        
        if discount_percent < 0 or discount_percent > 100:
            return False
        
        # 问题：计算错误，应该是 self.price * (1 - discount_percent/100)
        self.price = self.price - (self.price * discount_percent / 100)
        return True

# services/auth_service.py
from models.user import User

# 模拟数据库
users_db = {}

def register_user(username, password, email):
    # 问题：没有检查用户名是否已存在
    user = User(username, password, email)
    user.id = len(users_db) + 1
    users_db[username] = user
    return user

def authenticate_user(username, password):
    if username in users_db:
        user = users_db[username]
        # 问题：直接比较明文密码
        if user.password == password:
            return user
    return None

# services/product_service.py
from models.product import Product

# 模拟数据库
products_db = {}

def add_product(name, price, stock_count):
    product = Product(name, price, stock_count)
    product.id = len(products_db) + 1
    products_db[product.id] = product
    return product

def get_products():
    return list(products_db.values())

def get_product_by_id(product_id):
    if product_id in products_db:
        return products_db[product_id]
    return None

def update_product_stock(product_id, new_stock):
    product = get_product_by_id(product_id)
    if product:
        # 问题：没有验证 new_stock 是否为非负数
        product.stock_count = new_stock
        return True
    return False

# utils/helpers.py
import os
import time

def get_current_timestamp():
    return int(time.time())

def log_event(event_type, message):
    timestamp = get_current_timestamp()
    # 问题：日志路径使用硬编码
    log_file = "C:/logs/app.log"  
    
    # 问题：没有处理文件不存在的情况
    with open(log_file, "a") as f:
        f.write(f"{timestamp} - {event_type}: {message}\n")

def validate_email(email):
    # 问题：这是一个过于简单的邮箱验证
    return "@" in email and "." in email

# config.py
import os

# 环境变量
ENVIRONMENT = os.getenv("APP_ENV", "development")

# 数据库配置
DB_CONFIG = {
    "host": "localhost",
    "port": 3306,
    "user": "root",
    # 问题：硬编码的数据库密码
    "password": "admin123",  
    "database": "app_db"
}

# API配置
API_CONFIG = {
    "host": "0.0.0.0",  # 问题：应该限制为localhost用于开发环境
    "port": 5000,
    "debug": True  # 问题：生产环境不应该开启debug模式
}
