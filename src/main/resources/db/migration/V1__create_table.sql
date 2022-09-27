CREATE TABLE IF NOT EXISTS shortened_urls
(
    short_url    TEXT NOT NULL,
    original_url TEXT NOT NULL,
    title        TEXT NOT NULL,
    created_at   TIMESTAMP default now(),
    CONSTRAINT shortened_urls_PK PRIMARY KEY (short_url),
    CONSTRAINT shortened_urls_UK UNIQUE (original_url)
);