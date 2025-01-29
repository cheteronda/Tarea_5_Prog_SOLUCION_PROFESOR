package tarea05;

// ------------------------------------------------------------
//                   Clase Velero
// ------------------------------------------------------------
/**
 * <p>
 * Clase que representa un <strong>sistema de gestión de una escuela de vela</strong>
 * que se encarga de gestionar las diferentes operaciones de navegación
 * de los barcos veleros de la escuela.</p>
 * <p>
 * Los objetos de esta clase permiten almacenar y gestionar la información
 * relativa al estado de los veleros y a la navegación.</p>
 * <p>
 * La clase también posee información general (estática), relativa a los
 * diferentes barcos veleros que existen en la escuela, como:</p>
 * <ul>
 * <li><strong>cantidad total de veleros</strong> que existen en la escuela</li>
 * <li><strong>cantidad de veleros navegando</strong> en el momento actual</li>
 * <li><strong>cantidad total</strong> de minutos de navegación de todos los
 * veleros</li>
 * </ul>
 *
 * @author profesorado
 */
public class Velero {
    // ------------------------------------------------------------------------
    // Atributos estáticos públicos (inmutables)
    // Pueden ser accedidos desde cualquier caso
    // ------------------------------------------------------------------------
   
    /**
     * Número mínimo de mástiles de un velero: {@value MIN_MASTILES}.
     */
    public static final int MIN_MASTILES = 1;
    
    /**
     * Número máximo de mástiles de un velero: {@value MAX_MASTILES}.
     */
    public static final int MAX_MASTILES = 4;
    
    /**
     * Velocidad mínima de navegación (en nudos): {@value MIN_VELOCIDAD}.
     */
    public static final int MIN_VELOCIDAD = 2;

    /**
     * Velocidad máxima de navegación (en nudos): {@value MAX_VELOCIDAD}.
     */
    public static final int MAX_VELOCIDAD = 30;
    
    /**
     * Nombre del patrón por defecto durante la navegación: {@value PATRON_POR_DEFECTO}.
     */
    public static final String PATRON_POR_DEFECTO = "Sin patron";
    
    /**
     * Rumbo por defecto durante la navegación: {@value RUMBO_POR_DEFECTO}.
     */
    public static final String RUMBO_POR_DEFECTO = "Sin rumbo";
    
    /**
     * Número mínimo de tripulantes (sin incluir el patrón): {@value MIN_TRIPULANTES}.
     */
    public static final int MIN_TRIPULANTES = 0;

    // ------------------------------------------------------------------------
    // Atributos estáticos privados (mutables)
    // No dependen de instancias de objetos particulares y sólo pueden 
    // modificarse desde la propia clase
    // ------------------------------------------------------------------------
    // Cantidad total de barcos de la escuela
    private static int numBarcos = 0;

    // Cantidad total de barcos que están navegando
    private static int numBarcosNavegando = 0;

    // Número total de minutos de navegación de todos los barcos
    private static float tiempoTotalNavegacion = 0;

    // ------------------------------------------------------------------------
    // Atributos de objeto inmutables (privados)
    // Representan el estado del objeto pero no pueden cambiar su valor
    // ------------------------------------------------------------------------
    private final String nombreBarco;       // Nombre del barco   
    private final int numMastiles;          // Número de mástiles del velero
    private final int maxTripulantes;       // Número máximo de tripulantes del barco (sin incluir el patrón)

    // ------------------------------------------------------------------------
    // Atributos de objeto variables (privados)
    // Representan el estado del objeto y pueden cambiar su valor
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------
    // Atributos del estado del barco
    // ------------------------------------------------------------------------
    // Representan el estado básico del barco en un momento dado
    // ------------------------------------------------------------------------
    private boolean navegando;                   // Indica si el barco está navegando o no (TRUE / FALSE)
    private int tiempoTotalNavegacionBarco;      // Tiempo total de navegación del barco (en minutos)

    // ------------------------------------------------------------------------
    // Atributos de la información de navegación
    // ------------------------------------------------------------------------
    // Almacenan información sobre los parámetros de navegación
    // ------------------------------------------------------------------------
    private int velocidad;                  // Velocidad del barco (en nudos)
    private String patron;                  // Nombre del patron del barco
    private String rumbo;                   // Rumbo que tomará el barco mientras está navegando
    private int tripulacion;                // Número de tripulantes del barco

    // ------------------------------------------------------------------------
    // Constructores de la clase
    // ------------------------------------------------------------------------
    /**
     * Constructor de tres parámetros de la clase <code>Velero</code>.
     * Crea un objeto <code>Velero</code> y almacena los datos básicos del barco:
     * <b>nombre</b>, <b>mástiles</b> y <b>tripulantes</b>.
     * 
     * @param nombre Nombre del barco
     * @param mastiles Número de mástiles del velero
     * @param tripulantes Número máximo de tripulantes del barco
     * 
     * @throws NullPointerException Si algunos de los parámetros es nulo
     * @throws IllegalArgumentException Si alguno de los parámetros no es válido
     */
    public Velero(String nombre, int mastiles, int tripulantes) throws IllegalArgumentException, NullPointerException {
        if (nombre == null) {
            throw new NullPointerException("El nombre del velero no puede ser nulo.\n");
        }
        if (nombre.equals("")) {
            throw new IllegalArgumentException("El nombre del velero no puede estar vacío.\n");
        }
        //El número de mástiles del velero debe estar entre los mínimos y máximos que marca la clase
        if (mastiles < Velero.MIN_MASTILES || mastiles > Velero.MAX_MASTILES) {
            throw new IllegalArgumentException(String.format("El número de mástiles debe estar entre %d y %d.\n", Velero.MIN_MASTILES, Velero.MAX_MASTILES));
        }
        //El número máximo de tripulantes de un barco debe ser, como mínimo, cero (si únicamente puede navegar el patrón)
        if (tripulantes < Velero.MIN_TRIPULANTES) {
            throw new IllegalArgumentException(String.format("El número de tripulantes debe ser, como mínimo, %d.\n", Velero.MIN_TRIPULANTES));
        }
        this.nombreBarco = nombre;
        this.numMastiles = mastiles;
        this.maxTripulantes = tripulantes;
        
        // Inicialización de atributos relacionados con la navegación
        this.navegando = false;
        this.tiempoTotalNavegacionBarco = 0;
        this.velocidad = 0;
        this.patron = Velero.PATRON_POR_DEFECTO;
        this.rumbo = Velero.RUMBO_POR_DEFECTO;
        this.tripulacion = Velero.MIN_TRIPULANTES;

        // Actualización de los atributos de clase necesarios
        Velero.numBarcos++;
        
        //En el constructor no pasamos como parámetro un patrón, ya que un barco puede tener más de un patrón
        //Se asignará el patrón que está a cargo del barco cuando comience a navegar
    }
      
    /**
     * Constructor por defecto de la clase <code>Velero</code>.
     * Crea un objeto <code>Velero</code> con los valores por defecto.
     * 
     */
    public Velero() {
        this("Velero " + (Velero.numBarcos + 1), Velero.MIN_MASTILES, Velero.MIN_TRIPULANTES);
    }

    /**
     * Método fábrica de la clase <code>Velero</code>.
     * Crea un array de objetos <code>Velero</code> con los valores por defecto.
     * 
     * @param cantidad Número de barcos que se van a crear
     * @return El array de barcos
     * 
     * @throws IllegalArgumentException Si alguno de los parámetros no es válido
     */
    public static Velero[] crearArrayVelero(int cantidad) throws IllegalArgumentException {
        if (cantidad < 1 || cantidad > 10) {
            throw new IllegalArgumentException(String.format("Número de barcos incorrecto (%d), debe ser mayor o igual que 1 y menor o igual que 10.\n", cantidad));
        }

        Velero[] arrayBarcos = new Velero[cantidad];
        for (int i = 0; i < arrayBarcos.length; i++) {
            arrayBarcos[i] = new Velero();
        }
        return arrayBarcos;
    }

    // ------------------------------------------------------------------------
    // Getters (consultan el estado del objeto)
    // ------------------------------------------------------------------------
    
    /**
     * Método de acceso (getter) para consultar el atributo <code>nombreBarco</code>
     * @return Nombre del velero 
     */
    public String getNombreBarco() {
        return this.nombreBarco;
    }
    
    /**
     * Método de acceso (getter) para consultar el atributo <code>numMastiles</code>
     * @return Número de mástiles del velero
     */
    public int getNumMastiles() {
        return this.numMastiles;
    }
    
    /**
     * Método de acceso (getter) para consultar el atributo <code>maxTripulantes</code>
     * @return Número máximo de tripulantes del velero (sin incluir el patrón)
     */
    public int getMaxTripulantes() {
        return this.maxTripulantes;
    }
    
    /**
     * Método de acceso (getter) para consultar el atributo <code>navegando</code>
     * @return El velero está navegando o no (TRUE / FALSE)
     */
    public boolean isNavegando() {
        return this.navegando;
    }

    /**
     * Método de acceso (getter) para consultar el atributo <code>tiempoTotalNavegacionBarco</code>
     * @return Minutos de navegación del velero
     */
    public int getTiempoTotalNavegacionBarco() {
        return this.tiempoTotalNavegacionBarco;
    }

    //Getters que consultan información sobre la navegación
    
    /**
     * Método de acceso (getter) para consultar el atributo <code>velocidad</code>
     * @return Velocidad de navegación del velero
     */
    public int getVelocidad() {
        return this.velocidad;
    }

    /**
     * Método de acceso (getter) para consultar el atributo <code>rumbo</code>
     * @return Rumbo de navegación del velero
     */
    public String getRumbo() {
        return this.rumbo;
    }

    /**
     * Método de acceso (getter) para consultar el atributo <code>patron</code>
     * @return Patrón del velero
     */
    public String getPatron() {
        return this.patron;
    }

    /**
     * Método de acceso (getter) para consultar el atributo <code>tripulacion</code>
     * @return Número de tripulantes del velero
     */
    public int getTripulacion() {
        return this.tripulacion;
    }
    
    // ------------------------------------------------------------------------
    // Métodos estáticos (acceden a los atributos estáticos de la clase)
    // ------------------------------------------------------------------------

    /**
     * Método de acceso (getter) para consultar el atributo estático de clase <code>numBarcos</code>
     * @return Número de barcos en la escuela de vela
     */
    public static int getNumBarcos() {
        return Velero.numBarcos;
    }

    /**
     * Método de acceso (getter) para consultar el atributo estático de clase <code>numBarcosNavegando</code>
     * @return Número de barcos navegando en el momento actual
     */
    public static int getNumBarcosNavegando() {
        return Velero.numBarcosNavegando;
    }

    /**
     * Método de acceso (getter) para consultar el atributo estático de clase <code>tiempoTotalNavegacion</code>
     * @return Minutos de navegación de todos los barcos de la escuela de vela
     */
    public static float getTiempoTotalNavegacion() {
        return Velero.tiempoTotalNavegacion;
    }
    
    // ------------------------------------------------------------------------
    // Setters (modifican el estado del objeto)
    // ------------------------------------------------------------------------
    
    /**
     * Método para establecer el rumbo del Velero.
     * 
     * @param rumbo Rumbo en el que navega el barco: ceñida o empopada.
     * 
     * @throws NullPointerException Si el rumbo es nulo
     * @throws IllegalArgumentException Si alguno de los parámetros no es válido
     * @throws IllegalStateException Si el velero no se encuentra navegando
     * @throws IllegalStateException Si el velero ya se encuentra navegando en ese rumbo
     */
    public void setRumbo(String rumbo) throws IllegalStateException, NullPointerException, IllegalArgumentException{
        if (!this.isNavegando()) {
            throw new IllegalStateException(String.format("El velero %s no está navegando, no se puede cambiar el rumbo.\n", this.getNombreBarco()));
        }
        if (rumbo == null) {
            throw new NullPointerException("El rumbo no puede ser nulo, debes indicar el rumbo (ceñida o empopada) para poder modificarlo.\n");
        }
        //Comprobamos que el rumbo no esté vacío o sea distinto de "ceñida" o "empopada"
        if (rumbo.equals("") || (!rumbo.equals("ceñida") && !rumbo.equals("empopada"))) {
            throw new IllegalArgumentException("El rumbo no es correcto, debes indicar el rumbo (ceñida o empopada) para poder modificarlo.\n");
        }
        if (this.getRumbo().equals(rumbo)) {
            throw new IllegalStateException(String.format("El velero %s ya está navegando con ese rumbo (%s), debes indicar un rumbo distinto para poder modificarlo.\n", this.getNombreBarco(), this.getRumbo()));
        }
        this.rumbo = rumbo;
    }

    // ------------------------------------------------------------------------
    // Métodos de "acción" (almacenan la lógica y el comportamiento del objeto)
    // ------------------------------------------------------------------------
    
    /**
     * Inicia la navegación del barco actual
     * 
     * @param velocidad la velocidad del barco durante la navegación
     * @param rumbo el rumbo del barco durante la navegación
     * @param patron el patrón del barco en esta navegación
     * @param tripulacion el número de tripulantes (exceptuando el patrón) en esta navegación
     * 
     * @throws IllegalArgumentException Si la velocidad no está en el rango permitido
     * @throws IllegalStateException Si el barco ya se encuentra navegando o necesita mantenimiento
     * @throws NullPointerException Si patrón o rumbo tienen valores nulos.
     */
    public void iniciarNavegacion(int velocidad, String rumbo, String patron, int tripulacion) throws IllegalArgumentException, IllegalStateException, NullPointerException {
        if (velocidad < Velero.MIN_VELOCIDAD || velocidad > Velero.MAX_VELOCIDAD) {
            throw new IllegalArgumentException(String.format("La velocidad de navegación de %d nudos es incorrecta.\n", velocidad));
        }       
        if (this.isNavegando()) {
            throw new IllegalStateException(String.format("El velero %s ya está navegando y se encuentra fuera de puerto.\n", this.getNombreBarco()));
        }
        if (rumbo == null) {
            throw new NullPointerException("El rumbo no puede ser nulo, debes indicar el rumbo para iniciar la navegación.\n");
        }
        if (rumbo.equals("")) {
            throw new IllegalArgumentException("El rumbo no puede estar vacio, debes indicar el rumbo para iniciar la navegación.\n");
        }
        if (patron == null) {
            throw new NullPointerException("El patrón del barco no puede ser nulo, se necesita un patrón para iniciar la navegación.\n");
        }
        if (patron.equals("")) {
            throw new IllegalArgumentException("El patrón del barco no puede estar vacío, se necesita un patrón para iniciar la navegación.\n");
        }
        if (tripulacion < Velero.MIN_TRIPULANTES || tripulacion > this.maxTripulantes) {
            throw new IllegalArgumentException(String.format("El número de tripulantes debe estar entre %d y %d.\n", Velero.MIN_TRIPULANTES , this.getMaxTripulantes()));
        }
        
        this.velocidad = velocidad;
        this.rumbo = rumbo;
        this.patron = patron;
        this.tripulacion = tripulacion;
        this.navegando = true;
        Velero.numBarcosNavegando++;
    }

    /**
     * Para la navegación del barco actual
     * 
     * @param tiempoNavegando Tiempo (en minutos) que ha estado el barco navegando
     * 
     * @throws IllegalArgumentException Si el tiempo navegando es incorrecto
     * @throws IllegalStateException Si el barco no se encuentra navegando
     */
    public void pararNavegacion(int tiempoNavegando) throws IllegalStateException, IllegalArgumentException {
        if (!this.isNavegando()) {
            throw new IllegalStateException(String.format("El velero %s no está navegando.\n", this.getNombreBarco()));
        }
        if (tiempoNavegando <= 0) {
            throw new IllegalArgumentException("Tiempo navegando incorrecto, debe ser mayor que cero.\n");
        }
        this.tiempoTotalNavegacionBarco += tiempoNavegando;
        Velero.tiempoTotalNavegacion += tiempoNavegando;
        Velero.numBarcosNavegando--;
        this.navegando = false;
        this.velocidad = 0;
        this.rumbo = Velero.RUMBO_POR_DEFECTO;
        this.patron = Velero.PATRON_POR_DEFECTO;
        this.tripulacion = Velero.MIN_TRIPULANTES;
    }
    
    /**
     * Inicia una regata entre el barco actual y otro barco
     * 
     * @param otroBarco el barco contra el que se va a regatear
     * @return String El resultado de la regata
     * 
     * @throws IllegalStateException Si alguno de los barcos no está navegando o si no llevan el mismo rumbo.
     * @throws NullPointerException Si el barco que se pasa como parámetro es nulo.
     */
    public String iniciarRegata(Velero otroBarco) throws IllegalStateException, NullPointerException{
        // Comprobamos si el objeto pasado como prámetro es nulo
        if(otroBarco == null){
            throw new NullPointerException(String.format("El barco con el que se intenta regatear no existe.\n"));
        }
        // Comprobamos si ambos barcos se encuentran navegando
        if (!this.isNavegando()) {
            throw new IllegalStateException(String.format("No se puede iniciar la regata, el barco %s no está navegando.\n", this.getNombreBarco()));
        }
        if (!otroBarco.isNavegando()) {
            throw new IllegalStateException(String.format("No se puede iniciar la regata, el barco %s no está navegando.\n", otroBarco.getNombreBarco()));
        }
        // Comprobamos si ambos barcos llevan el mismo rumbo
        if (!this.getRumbo().equals(otroBarco.getRumbo())){
            throw new IllegalStateException(String.format("No se puede iniciar la regata, los barcos %s y %s deben navegar con el mismo rumbo.\n", this.getNombreBarco(), otroBarco.getNombreBarco()));
        }
        // Comprobamos si ambos barcos tienen el mismo numero de mastiles
        if (this.getNumMastiles() != otroBarco.getNumMastiles()){
            throw new IllegalStateException(String.format("No se puede iniciar la regata, los barcos %s y %s no tienen el mismo numero de mástiles.\n", this.getNombreBarco(), otroBarco.getNombreBarco()));
        }
        
        // Iniciamos la regata
        String resultadoRegata;
        
        // Comparamos las velocidades para averiguar cuál es el barco más rápido, que será el que gane la regata
        if (this.velocidad > otroBarco.getVelocidad()) {
            resultadoRegata = String.format("El barco %s ha llegado antes a la línea de llegada.\n", this.getNombreBarco());
        } else if (this.velocidad < otroBarco.getVelocidad()) {
            resultadoRegata = String.format("El barco %s ha llegado primero a la línea de llegada.\n", otroBarco.getNombreBarco());
        } else {
            resultadoRegata = String.format("Los barcos %s y %s han llegado a la vez a la línea de llegada.\n", this.getNombreBarco(), otroBarco.getNombreBarco());
        }
        return resultadoRegata;
    }
    
    // ------------------------------------------------------------------------
    // Método toString (imprime el estado del objeto)
    // ------------------------------------------------------------------------
    
    /**
     * Devuelve el estado del objeto en un mensaje formateado en un tipo String
     *
     * @return String Estado del objeto
     */
    @Override
    public String toString() {
        return String.format("{Nombre del barco: %s, Número de mástiles: %d, Tripulación: %d, Navegando: %s, Tiempo total de navegación del barco: %.2f horas}",
                this.getNombreBarco(),
                this.getNumMastiles(),
                this.getTripulacion(),
                (this.isNavegando() ? String.format("Sí, con el patrón %s en %s a %d nudos", this.getPatron(),this.getRumbo(),this.getVelocidad()): "No"),
                (float) this.getTiempoTotalNavegacionBarco() / 60.0
        );
    }
}