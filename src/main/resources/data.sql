-- 고객
INSERT INTO customers (name, email, phone, status, created_at, updated_at)
VALUES
    ('정예진', 'jin@test.com', '010-1111-1111', 'ACTIVE', NOW(), NOW()),
    ('이재석', 'lee@test.com', '010-2222-2222', 'ACTIVE', NOW(), NOW()),
    ('민병준', 'min@test.com', '010-3333-3333', 'INACTIVE', NOW(), NOW());