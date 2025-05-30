/* Script d'insertion pour la table PRODUCT */
INSERT INTO PRODUCT (code, name, description, image, category, price, quantity, internal_reference, shell_id, inventory_status, rating, created_at, updated_at) 
VALUES 
  ('PROD-001', 'Ordinateur Portable', 'PC haute performance', 'laptop.jpg', 'Électronique', 999.99, 10, 'REF-INT-001', 1001, 'INSTOCK', 4.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  
  ('PROD-002', 'Smartphone Premium', 'Écran 6.7" 120Hz', 'phone.jpg', 'Téléphonie', 799.99, 5, 'REF-INT-002', 1002, 'LOWSTOCK', 4.8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  
  ('PROD-003', 'Casque Audio', 'Réduction de bruit active', 'headphones.jpg', 'Audio', 349.99, 0, NULL, NULL, 'OUTOFSTOCK', 4.2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  
  ('PROD-004', 'Livre Spring Boot', 'Guide complet Spring Boot 3', 'book.jpg', 'Livres', 39.99, 20, 'REF-INT-004', NULL, 'INSTOCK', 4.7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  
  ('PROD-005', 'Chaise de Bureau', 'Ergonomique en cuir', 'chair.jpg', 'Mobilier', 299.99, 8, 'REF-INT-005', 1005, 'LOWSTOCK', 4.3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

/* Script d'insertion pour la table app_user */
INSERT INTO app_user (username, firstname, email, password) VALUES
  ('admin', 'Admin', 'admin@admin.com', '$2a$12$PyVK2p90g2A7OG.Ay9Yj9uHfH62SvuR5MrpPgffmiz27RmdDmuHU6'), -- password = admin123
  ('youssef', 'Maa', 'youssef@example.com', '$2a$12$/NQZIZQA/ReKvKRR48.AHub7OPpkl5XOo7gpMQpSMSuKakH000Xme');  -- password = youssefpass
  
-- Création des wishlists pour les utilisateurs
INSERT INTO wishlist (id, user_id) VALUES 
  (1, (SELECT id FROM app_user WHERE email = 'admin@admin.com')),
  (2, (SELECT id FROM app_user WHERE email = 'youssef@example.com'));

-- Association des produits aux wishlists (wishlist_products est la table de jointure)
INSERT INTO wishlist_products (wishlist_id, products_id) VALUES
  (1, (SELECT id FROM product WHERE code = 'PROD-001')), -- Admin
  (1, (SELECT id FROM product WHERE code = 'PROD-003')), -- Admin
  (2, (SELECT id FROM product WHERE code = 'PROD-002')), -- Youssef
  (2, (SELECT id FROM product WHERE code = 'PROD-004')); -- Youssef