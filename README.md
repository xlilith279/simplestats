# SimpleStats

**SimpleStats** es un plugin para Minecraft que permite realizar un seguimiento de las estadísticas de los jugadores en varios aspectos. Actualmente, el plugin registra las siguientes estadísticas:

- **Player Kills**: El número total de jugadores asesinados por un jugador.
- **Mob Kills**: El número total de mobs (criaturas) asesinados por un jugador.
- **Deaths**: El número total de muertes de un jugador.
- **Animal Bred**: El número de animales criados por un jugador.  
  **Nota**: Este placeholder actualmente no funciona correctamente y será corregido en futuras versiones.

### Configuración de Mundos

Puedes configurar el plugin para que registre estadísticas solo en los mundos que elijas. Asegúrate de definir los mundos en la configuración para personalizar dónde se aplican las estadísticas.

### Placeholders

Aquí tienes los placeholders disponibles para mostrar las estadísticas de los jugadores y su ranking:

#### Placeholders Generales

- **`%simplestats_player_kills%`**  
  Muestra el número total de *kills* de un jugador.

- **`%simplestats_mob_kills%`**  
  Muestra el número total de *kills* de mobs (criaturas) de un jugador.

- **`%simplestats_deaths%`**  
  Muestra el número total de *muertes* de un jugador.

- **`%simplestats_animal_bred%`**  
  Muestra el número de animales criados por el jugador.  
  **Nota**: Este placeholder puede necesitar corrección, ya que actualmente no está funcionando correctamente.

#### Placeholders del Ranking

- **`%simplestats_topkills_name_<position>%`**  
  Muestra el nombre del jugador en la posición `<position>` en el ranking de *player kills*.  
  **Ejemplo**: `%simplestats_topkills_name_1%` para el jugador en la primera posición.

- **`%simplestats_topkills_kills_<position>%`**  
  Muestra el número de *kills* que tiene el jugador en la posición `<position>` en el ranking de *player kills*.  
  **Ejemplo**: `%simplestats_topkills_kills_1%` para el número de *kills* del jugador en la primera posición.

### Comandos

- **`/simplestats reload`**  
  Recarga la configuración y reinicia los registros del plugin. **Importante**: Al usar este comando, la Placeholder API se recarga, lo que puede eliminar los registros actuales. Este comando se asegura de volver a registrar correctamente los placeholders después de una recarga.

---

### Futuras Actualizaciones

En futuras versiones de **SimpleStats**, se añadirán más estadísticas para ofrecer un seguimiento más detallado del rendimiento de los jugadores.

Si tienes alguna sugerencia o encuentras un error, no dudes en informarnos. ¡Estamos trabajando para mejorar el plugin!
