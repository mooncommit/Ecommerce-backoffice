# Ecommerce Backoffice ERD

아래 ERD는 현재 엔티티 구조를 기준으로 정리한 심플 버전입니다.

```mermaid
%%{init: {
  "theme": "base",
  "themeVariables": {
    "background": "#f7f7f5",
    "primaryColor": "#ffffff",
    "primaryTextColor": "#1f2937",
    "primaryBorderColor": "#d1d5db",
    "lineColor": "#9ca3af",
    "fontFamily": "Pretendard, Apple SD Gothic Neo, sans-serif"
  }
}}%%
erDiagram
    ADMIN ||--o{ PRODUCT : registers
    ADMIN o|--o{ ORDER : processes
    CUSTOMER ||--o{ ORDER : places
    ORDER ||--|{ ORDER_ITEM : contains
    PRODUCT ||--o{ ORDER_ITEM : referenced_by

    ADMIN {
        BIGINT id PK
        VARCHAR name
        VARCHAR email UK
        VARCHAR password
        VARCHAR phone
        ENUM role
        ENUM status
        DATETIME approved_at
        DATETIME rejected_at
        TEXT rejection_reason
        DATETIME created_at
        DATETIME updated_at
    }

    CUSTOMER {
        BIGINT id PK
        VARCHAR name
        VARCHAR email UK
        VARCHAR phone
        ENUM status
        DATETIME created_at
        DATETIME updated_at
    }

    PRODUCT {
        BIGINT id PK
        BIGINT admin_id FK
        VARCHAR name
        ENUM category
        INT price
        INT stock
        ENUM status
        DATETIME created_at
        DATETIME updated_at
    }

    ORDER {
        BIGINT id PK
        VARCHAR order_number UK
        BIGINT customer_id FK
        BIGINT admin_id FK
        INT total_price
        ENUM status
        TEXT cancel_reason
        DATETIME created_at
        DATETIME updated_at
    }

    ORDER_ITEM {
        BIGINT id PK
        BIGINT order_id FK
        BIGINT product_id FK
        VARCHAR product_name
        INT product_price
        INT quantity
    }
```

## 관계 요약

- `Admin` 1명은 여러 `Product`를 등록할 수 있습니다.
- `Customer` 1명은 여러 `Order`를 가질 수 있습니다.
- `Order` 1건은 여러 `OrderItem`을 포함합니다.
- `Product` 1개는 여러 `OrderItem`에서 참조될 수 있습니다.
- `Order.admin_id`는 주문 처리 담당 관리자를 의미하며 nullable 입니다.
