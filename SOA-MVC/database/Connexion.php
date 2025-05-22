<?php
class Connexion {
    private static ?PDO $conexion = null;

    public static function connect(): PDO {
        if (self::$conexion === null) {
            define('SERVER', "localhost");
            define('DATABASE', 'SOA');
            define('USERNAME', 'root');
            define('PASSWORD', '');
            $options = [PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8'];

            try {
                self::$conexion = new PDO("mysql:host=".SERVER.";dbname=".DATABASE, USERNAME, PASSWORD, $options);
                self::$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            } catch (PDOException $e) {
                die("Error de conexión: " . $e->getMessage());
            }
        }
        return self::$conexion;
    }
}
?>
