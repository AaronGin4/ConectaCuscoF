-- Datos iniciales para la aplicación Conecta Cusco

-- Insertar roles
INSERT INTO roles (name) VALUES ('ROLE_USER') ON DUPLICATE KEY UPDATE name = name;
INSERT INTO roles (name) VALUES ('ROLE_ADMIN') ON DUPLICATE KEY UPDATE name = name;

-- Insertar categorías de servicios
INSERT INTO categoria_servicio (nombre, descripcion) VALUES 
('Turismo', 'Servicios turísticos en Cusco'),
('Gastronomía', 'Restaurantes y servicios de comida'),
('Transporte', 'Servicios de transporte y movilidad'),
('Artesanía', 'Productos artesanales y souvenirs'),
('Alojamiento', 'Hoteles, hostales y hospedaje');

-- Insertar usuario administrador (opcional)
-- INSERT INTO usuarios (email, nombre, password, username) VALUES 
-- ('admin@conectacusco.com', 'Administrador', '$2a$10$tu_hash_aqui', 'admin');
-- INSERT INTO usuario_roles (usuario_id, role_id) VALUES (1, 2); 