-- ユーザーテーブル作成
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ユーザーID',
    user_name VARCHAR(50) NOT NULL UNIQUE COMMENT 'ユーザー名',
    password VARCHAR(255) NOT NULL COMMENT 'パスワード（ハッシュ化済み）',
    role VARCHAR(20) NOT NULL DEFAULT 'USER' COMMENT 'ロール',
    enabled BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'アカウント有効フラグ',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    INDEX idx_user_name (user_name),
    INDEX idx_enabled (enabled),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='ユーザー情報テーブル';

-- 初期データ挿入
-- パスワード: password123 (BCryptでハッシュ化)
INSERT INTO users (user_name, password, role, enabled) VALUES 
('admin', '$2a$10$lfGUycjsB.YZ9MOYI6CouucRljbrSUdYpmFxY3fmn52THf3f.gOnG', 'ADMIN', TRUE),
('testuser', '$2a$10$lfGUycjsB.YZ9MOYI6CouucRljbrSUdYpmFxY3fmn52THf3f.gOnG', 'USER', TRUE),
('demo', '$2a$10$lfGUycjsB.YZ9MOYI6CouucRljbrSUdYpmFxY3fmn52THf3f.gOnG', 'USER', TRUE);