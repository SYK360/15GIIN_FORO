import java.util.*;

import java.io.*;


//Clase BinarySearchTree
//
//
//******************METODOS PUBLICOS*********************
//void insert( x )       --> Inserta x
//void remove( x )       --> Elimina x
//void removeMin( )      --> Elimina el objeto con menor valor (clave)
//Comparable find( x )   --> Devuelve el objeto del arbol igual a x
//Comparable findMin( )  --> devuelve el objeto menor
//Comparable findMax( )  --> devuelve el objeto mayor
//boolean isEmpty( )     --> devuelve TRUE sii el arbol es vacio
//void makeEmpty( )      --> elimina todos los nodos del arbol
//boolen contains( X )   --> devuelve verdad si X estÃ¡ en el arbol

/**
 * Implementa Arbol Binario de BÃºsqueda
 * Como comparador por default es compareTo
 * Pero podemos pasarle un Comparator al momento
 * de crear el arbol
 *
 */
public class BinarySearchTree<AnyType extends
		Comparable<? super AnyType>>
{
	private BinaryNode<AnyType> root;
	private Comparator<? super AnyType> cmp; // para poder tener
	// otra forma de comparar claves
	// que no sea la dada por el
	// metodo compareTo de AnyType
	// que hereda de la interfaz Comparable
	// podemos tener un "comparator"

	BinarySearchTree( )
	{ this( null ); }  // llamar al siguiente constructor
	// (el siguiente) con comparador null

	BinarySearchTree( Comparator<? super AnyType> c )
	// en este constructor podemos pasar un Comparador (Comparator)
	{ root = null; cmp = c; }


	// La clase BinaryNode para crear nodos
	// Note que por no ser ni publica ni privada
	// solo es accesible en el paquete

	static class BinaryNode<AnyType>
	{

		private AnyType             element;
		private BinaryNode<AnyType> left;
		private BinaryNode<AnyType> right;

		public BinaryNode( )
		{
			this( null, null, null );
		}

		public BinaryNode(AnyType x )
		{
			this( x, null, null );
		}

		public BinaryNode( AnyType theElement,
						   BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
		{
			element = theElement;
			left    = lt;
			right   = rt;
		}


		public AnyType getElement( )
		{
			return element;
		}

		public BinaryNode<AnyType> getLeft( )
		{
			return left;
		}

		public BinaryNode<AnyType> getRight( )
		{
			return right;
		}

		public void setElement( AnyType x )
		{
			element = x;
		}

		public void setLeft( BinaryNode<AnyType> t )
		{
			left = t;
		}

		public void setRight( BinaryNode<AnyType> t )
		{
			right = t;
		}

	}
	private int myCompare( AnyType lhs, AnyType rhs )
	// Siempre que comparemos elementos utilizamos este
	// metodo de comparacion de elementos. Si existe
	// un Comparator se usa este para comparar, si no
	// se usa compareTo de la interfaz Comparable
	// de esta forma podemos comparar por nombre, DNI, etc
	// el default es DNI que es por donde se compara
	// en el metodo compareTo de la interfaz Comparable
	// Ese metodo devuelve
	//      un numero menor que cero si lhs es menor que rhs
	//      un numero igual a cero si lhs es igual a rhs
	//      un numero mayor que cero si lhs es mayor que rhs
	{
		if( cmp != null )
			return cmp.compare( lhs, rhs );
		else
			return lhs.compareTo( rhs );
	}

	public boolean contains( AnyType x )
	{ return contains( x, root ); }  // un mÃ©todo â€œdriverâ€ para obtener la raÃ­z

	private boolean contains( AnyType x,
							  BinaryNode<AnyType> t )
	{   // version recursiva
		if( t == null )
			return false;    // Caso base: No encontrado

		int compareResult = myCompare(x, t.getElement());
		if( compareResult < 0 )
			return contains( x, t.getLeft() );
		else if( compareResult > 0 )
			return contains( x, t.getRight() );
		else
			return true; // Encontrado
	}

	/**
	 * Encontrar el menor elemento
	 * @return devuelve el menor elemento o null si esta vacio.
	 */
	public AnyType findMin( )
	{
		BinaryNode<AnyType>   nodo  = findMin( root ) ;
		return   nodo == null  ? null :   nodo.getElement();
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * @param t the node that roots the tree.
	 * @return node containing the smallest item.
	 */
	private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
	{
		if( t != null )
			while( t.left != null )
				t = t.left;

		return t;
	}

	private BinaryNode<AnyType> getRoot(){
		// obtener la referencia al nodo raÃ­z
		return root;
	}

	/**
	 * convertir el arbol en vacio
	 */
	public void makeEmpty( )
	{
		root = null;
	}

	/**
	 * Preguntar si el arbol es vacio
	 * @return devuelve TRUE sii es vacio.
	 */
	public boolean isEmpty( )
	{
		return root == null;
	}

	/**
	 * Metodo para determinar el numero de nodos del arbol.
	 * @param t es el ndo.
	 * @return devuelve el elemento o null si el nodo es null.
	 */
	public int num_nodos() { //metodo driver
		// devuelve el numero de nodos del arbol (this)
		return num_nodos(root);
	}
	private int num_nodos(BinaryNode<AnyType> nodo) {
		// devuelve el  numero de nodos del arbol cuya raiz es nodo
		if (nodo==null) return 0;
		return 1+num_nodos(nodo.getLeft())+num_nodos(nodo.getRight());

	}


	/**
	 * Buscar un elemento en el arbol.
	 * Metodo DRIVER de un algoritmo recursivo
	 * @param x es el elemento a buscar.
	 * @return devuelve el elemento o null si no existe.
	 */
	public AnyType find( AnyType x )
	{
		BinaryNode<AnyType>   nodo  = find( x,root ) ;
		return   nodo == null  ? null :   nodo.getElement();
	}

	/**
	 * Internal method to find an item in a subtree.
	 * @param x is item to search for.
	 * @param t the node that roots the tree.
	 * @return node containing the matched item.
	 */
	private BinaryNode<AnyType> find( AnyType x, BinaryNode<AnyType> t )
	{
		while( t != null )
		{
			if( myCompare(x, t.element ) < 0 )
				t = t.left;
			else if( myCompare(x, t.element ) > 0 )
				t = t.right;
			else
				return t;    // Match
		}

		return null;         // Not found
	}


	/**
	 * Insertar (o agregar) el objeto x al arbol
	 * @param x el elemento a insertar.
	 */
	public void insert( AnyType x )
	{  // metodo DRIVER que llama a uno recurviso privado
		root = insert( x, root );
	}

	/**
	 * Internal method to insert into a subtree.
	 * @param x the item to insert.
	 * @param t the node that roots the tree.
	 * @return the new root.
	 * @throws DuplicateItemException if x is already present.
	 */
	private BinaryNode<AnyType> insert( AnyType x,
										BinaryNode<AnyType> t )
	{
		int valor;
		if( t == null )
			t = new BinaryNode<AnyType>( x );
		else if ( (valor = myCompare(x, t.element)) < 0 )
			t.left = insert( x, t.left );
		else if ( valor > 0 )
			t.right = insert( x, t.right );
		else; // no inserta nada porque el elemento ya existe;
		// se ha podido quetar este else pero lo dejo para indicar que en ese punto
		// consigue que el elemento ya esta en el arbol
		return t;
	}

	/**
	 * Eliminar un objeto del arbol..
	 * @param x el objeto a eliminar
	 */
	public void remove( AnyType x )
	{   // metodo DRIVER que llama al recursivo privado
		root = remove( x, root );
	}

	/**
	 * Internal method to remove from a subtree.
	 * @param x the item to remove.
	 * @param t the node that roots the tree.
	 * @return the new root.
	 * @throws ItemNotFoundException if x is not found.
	 */
	private BinaryNode<AnyType> remove( AnyType x,
										BinaryNode<AnyType> t )
	{
		if( t == null );
		// no haga nada porque el elemento no existe;
		if( myCompare(x, t.element ) < 0 )
			t.left = remove( x, t.left );
		else if( myCompare(x, t.element ) > 0 )
			t.right = remove( x, t.right );
		else if( t.left != null && t.right != null ) // Two children
		{
			t.element = findMin( t.right ).element;
			t.right = removeMin( t.right );
		}
		else
			t = ( t.left != null ) ? t.left : t.right;
		return t;
	}


	/**
	 * Eliminar el menor elemento
	 */
	public void removeMin( )
	{
		root = removeMin( root );
	}

	/**
	 * Internal method to remove minimum item from a subtree.
	 * @param t the node that roots the tree.
	 * @return the new root.
	 * @throws ItemNotFoundException if t is empty.
	 */
	private BinaryNode<AnyType> removeMin( BinaryNode<AnyType> t )
	{
		if( t == null ) return t;
			// no haga nada porque el elemento no existe;
		else if( t.left != null )
		{
			t.left = removeMin( t.left );
			return t;
		}
		else
			return t.right;
	}

	/**
	 * Encontrar el mayor elemento del arbol
	 * @return devuelve el mayor elemento o null si es vacio.
	 */
	public AnyType findMax( )
	{
		BinaryNode<AnyType>   nodo  = findMax( root ) ;
		return   nodo == null  ? null :   nodo.getElement();
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * @param t the node that roots the tree.
	 * @return node containing the largest item.
	 */
	private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
	{
		if( t != null )
			while( t.right != null )
				t = t.right;

		return t;
	}


	public int containsTimesElement( AnyType x ) // metodo driver
	{
		return containsTimesElement( x, root );
	}

	private int containsTimesElement( AnyType x, BinaryNode<AnyType> t )
	{
		if( t == null )
			return 0;    //El nodo no pudo ser encontrado, se retorna 0

		// Condición ternaria:
		// Si el elemento actual del nodo es igual al elemento buscado (t.getElement().equals(x)), sumamos 1.
		// Si no es igual, sumamos 0.
		// A esto, sumamos recursivamente el resultado de la búsqueda en el subárbol izquierdo (t.getLeft()).
		// Luego, sumamos recursivamente el resultado de la búsqueda en el subárbol derecho (t.getRight()).
		return (t.getElement().equals(x) ? 1 : 0) +
				containsTimesElement(x, t.getLeft()) +
				containsTimesElement(x, t.getRight());
	}


	public int num_hojas() {  // metodo driver
		// devuelve el numero de hojas del arbol
		return num_hojas(root);
	}

	private int num_hojas(BinaryNode<AnyType> nodo) {
		//  devuelve el numero de hojas del arbol cuya raiz es nodo
		if (nodo == null) return 0;
		if ((nodo.getLeft()==null)&&(nodo.getRight()==null)) return 1;
		return num_hojas(nodo.getLeft()) + num_hojas(nodo.getRight());
	}

	public int menoresQue(AnyType elemento) {
		// devuelve el numero de elementos menores que elemento
		// es un metodo driver del metodo recursivo
		// con ingual nombre
		return menoresQue(root,elemento);
	}
	public int menoresQue(BinaryNode<AnyType> nodo,AnyType ELEMENTO) {
		// devuelve el numero de elementos menores que ELEMENTO
		// en el arbol cuya raiz es nodo
		if (nodo==null) return 0;
		int cmp = myCompare(ELEMENTO,nodo.getElement());
		if (cmp==0) return num_nodos(nodo.getLeft());
		if (cmp<0)
			return menoresQue(nodo.getLeft(), ELEMENTO);

		return 1  +  num_nodos(nodo.getLeft())
				+  menoresQue(nodo.getRight(), ELEMENTO);
	}

	// Estos mÃ©todos deben estar en la clase BinaryTree 
	// si la Clase BinaryNode es interna a BinaryTree y privada
	// porque hacen uso de metodos y campos de la clase BinaryNode

	// Determina el nivel de un elemento
	public int nivel(AnyType elem){

		return  nivel(getRoot(), elem, 0);
	}

	private int nivel(BinaryNode<AnyType> nodo, AnyType elem, int res){

		if (nodo== null)
			return -1; // no encontrado

		AnyType r = nodo.getElement();

		int valor = myCompare(elem,r);

		if (valor == 0) return res;

		if (valor<0) return nivel(nodo.getLeft(),elem,1+res);

		else {  return nivel(nodo.getRight(),elem,1+res); }

	}
	// Imprimir el arbol en Pre Orden.
	public void printPreOrder( )
	{
		if (root != null) printPreOrderR(root);
	}
	private static <Otro> void printPreOrderR(BinaryNode<Otro> nod )
	{
		System.out.println( nod.getElement() );       // Node
		if( nod.getLeft() != null )
			printPreOrderR( nod.getLeft() );          // Left
		if( nod.getRight() != null )
			printPreOrderR( nod.getRight() );         // Right
	}

	// Imprimir el arbol en In Orden (la impresion sera
	// la lista de los elementos del arbol ordenados
	// en orden creciente)
	public void printInOrder( )
	{
		if (root != null) printInOrderR(root);
	}
	private static <Otro> void printInOrderR(BinaryNode<Otro> nod )
	{
		if( nod.getLeft() != null )
			printInOrderR( nod.getLeft() );          // Left
		System.out.println( nod.getElement() );       // Node
		if( nod.getRight() != null )
			printInOrderR( nod.getRight() );         // Right
	}

	public boolean test_AVL() {
		// permite probar si un arbol binario de busqueda es AVL
		// es decir, los subarboles izquierdo y derecho son
		// AVL y la diferencia de las alturas de los
		// subarboles izq y der es a lo sumo 1
		// Los arboles null y con un solo nodo son AVL

		element_AVL elem = test_AVL(root);
		return elem.test;
	}
	class element_AVL{
		boolean test;
		int altura;
	}
	private element_AVL test_AVL(BinaryNode<AnyType> n) {
		// este algoritmo devuelve un objeto tipo element_AVL
		// que consiste de dos campos: test y altura
		// el algoritmo determina si el  rbol cuya ra z es n cumple con que
		// para cada nodo las alturas de sus subÃ¡rboles derecho e izquierdo 
		// difieren en a lo sumo 1. De ser este verdad, se devuelve el
		// objeto elemnt_AVL con valor (true, altura del Ã¡rbol)
		// de ser falso se devuelve (false, xxx) xxx representa un valor que
		// no necesariamente es la altura del Ã¡rbol cuya raÃ­z es n
		// El algoritmo es recursivo y muy sencillo:
		// - determina si el subÃ¡rbol izquierdo cumple con la propiedad:
		//		para cada nodo las alturas de sus subÃ¡rboles derecho e izquierdo 
		// 		difieren en a lo sumo 1
		// - determina si el subÃ¡rbol derecho cumple con la propiedad:
		//		para cada nodo las alturas de sus subÃ¡rboles derecho e izquierdo 
		// 		difieren en a lo sumo 1
		// - de ser cierto que los subÃ¡rboles izquierdo y derecho de n cumplen
		//	la propiedad, entonces 
		//		- si las alturas de los subÃ¡rboles difieren en menos de 2 se devuelve
		//			(verdad, altura del Ã¡rbol cuya raÃ­z es n) 
		//		- si las alturas de los subÃ¡rboles difieren en al menos 2 se devuelve
		//			(false, altura del Ã¡rbol cuya raÃ­z es n) 
		//	Nota: la altura del Ã¡rbol cuya raÃ­z es n es:  
		//			1+ max(altura izquierdo, altura derecho)


		element_AVL elem = new element_AVL();
		element_AVL elem1 = new element_AVL();
		element_AVL elem2 = new element_AVL();
		if (n==null) { elem.test=true; elem.altura=-1;}
		else {
			elem1 = test_AVL(n.getLeft());
			// el siguiente if permite que sea mas eficiente el proceso
			// pues una vez que uno de los subÃ¡rboles no cumpla 
			// con la diferencia de alturas menor o igual que  1 para cada uno de sus
			// nodos, entonces no vale la pena continuar, sino que se
			// devuelve que el Ã¡rbol cuya raÃ­z es n tampoco cumple con 
			// diferencia de alturas menor o igual que 1  para cada nodo
			// y ya no importa calcular su altura
			if (elem1.test==false) return elem1;

			elem2 = test_AVL(n.getRight());
			// el siguiente if permite que sea mas eficiente el proceso
			// pues una vez que uno de los subÃ¡rboles no cumpla 
			// con la diferencia de alturas menor o igual que  1 para cada uno de sus
			// nodos, entonces no vale la pena continuar, sino que se
			// devuelve que el Ã¡rbol cuya raÃ­z es n tampoco cumple con 
			// diferencia de alturas menor o igual que 1  para cada nodo
			// y ya no importa calcular su altura
			if (elem2.test==false) return elem2;
			elem.test = ( Math.abs(elem1.altura-elem2.altura)<=1  );
			elem.altura = 1 + Math.max(elem1.altura,elem2.altura);
		}
		return elem;
	}

	// Programa de prueba
	// Lee un archivo "archivo.txt" con informaciÃ³n de 
	// estudiantes y empleados. Los guarda en un BinarySearchTree
	// de acuerdo a la clave que queramos (name o DNI)
	// Y luego los imprime utilizand un recorrido en In-Orden
	// El formato del archivo es como sigue (un ejemplo):
	//	S                   para indicar que es un estudiante
	//	12345679            dni
	//	martha pÃ©rez        nombre y apellido
	//	34                  edad
	//	58-4126785453       telf
	//	9.5                 promedio de calificaciones
	//	E                   para indicar que es empleado
	//	12345661
	//	carlos pÃ©rez
	//	33
	//	34-678445533
	//	2000.34             sueldo
	//	E
	//	12345662
	//	pedro pÃ©rez
	//	33
	//	34-678445533
	//	2000.34
	//	S
	// ......

	public static void main( String [ ] args ) throws IOException
	{
		BinarySearchTree<Person> t = new BinarySearchTree<Person>(  );
		// ordena por DNI pues compareTo en la clase Persona
		// compara los DNI

		Scanner in = new Scanner(new File("archivo.txt"));
		// podriamos haber puesto "..\\archivo.txt" si el archivo esta en el directorio
		// padre al default
		// el archivo debe estar en el directorio del proyecto MisClases
		String line = new String();
		String tipo = new String();
		String dni = new String();
		String nombre = new String();
		Integer edad ;
		String telf = new String();
		Double calif_o_salario ;
		System.out.println( "Listado DEL ARCHIVO: " );
		// los archivos de texto deben estar en codigo ANSI
		// o UTF-8 sin BOOM (??????)   y no estÃ© en  UTF-8 simple
		// PORQUE LA PRIMERA LINEA DEL ARCHIVO EN UTF-8
		// EL PRIMER CARACTER AUNQUE NO LO VEMOS EN EL EDITOR NOTEPAD++
		// ES UN CARACTER EXTRAÃ±O COMO  Ã¯Â»Â¿
		// PASE MAS DE DOS HORAS ENTERANDOME DE ESTO!!!
		// en NotePad++ no se ve el caracter y en ningÃºn otro editor
		// https://stackoverflow.com/questions/17405165
		//        /first-character-of-the-reading-from-the-text-file-%C3%AF
		while (in.hasNextLine()) {
			line = in.nextLine();
			tipo = line.split(" ")[0];
			if (tipo.equals("S")) {
				line = in.nextLine();
				dni = line.split(" ")[0];
				nombre = in.nextLine();
				line = in.nextLine();
				edad = Integer.parseInt(line.split(" ")[0]);
				line = in.nextLine();
				telf = line.split(" ")[0];
				line = in.nextLine();
				calif_o_salario = Double.parseDouble(line.split(" ")[0]);
				Student est = new Student(dni,nombre,edad,telf,
						calif_o_salario);
				System.out.println("estudiante "+dni+" "+nombre+" "+
						edad+" "+telf+" "+calif_o_salario);
				t.insert(est);
			}
			if (tipo.equals("E")) {
				line = in.nextLine();
				dni = line.split(" ")[0];
				nombre = in.nextLine();
				line = in.nextLine();
				edad = Integer.parseInt(line.split(" ")[0]);
				line = in.nextLine();
				telf = line.split(" ")[0];
				line = in.nextLine();
				calif_o_salario = Double.parseDouble(line.split(" ")[0]);
				Employee emp = new Employee(dni,nombre,edad,telf,
						calif_o_salario);
				System.out.println("empleado "+dni+" "+nombre+" "+
						edad+" "+telf+" "+calif_o_salario);
				t.insert(emp);
			}
		}
		System.out.println( "\nListados en preorden: " );
		t.printPreOrder();
		System.out.println( "\nOrdenados por DNI: " );
		t.printInOrder();

		in.close();

		BinarySearchTree<Person> t1 = new BinarySearchTree<Person>
				( new OrderPersonByName() );
		// ordena por nombre usa el COMPARATOR OrderPersonByName
		// aunque nombre no es buena clave porque puede haber
		// nombre repetidos, y es la clave para insertar los elementos
		in = new Scanner(new FileReader("archivo.txt"));

		while (in.hasNextLine()) {
			line = in.nextLine();
			tipo = line.split(" ")[0];
			if (tipo.equals("S")) {
				line = in.nextLine();
				dni = line.split(" ")[0];
				nombre = in.nextLine();
				line = in.nextLine();
				edad = Integer.parseInt(line.split(" ")[0]);
				line = in.nextLine();
				telf = line.split(" ")[0];
				line = in.nextLine();
				calif_o_salario = Double.parseDouble(line.split(" ")[0]);
				Student est = new Student(dni,nombre,edad,telf,
						calif_o_salario);
				t1.insert(est);
			}
			if (tipo.equals("E")) {
				line = in.nextLine();
				dni = line.split(" ")[0];
				nombre = in.nextLine();
				line = in.nextLine();
				edad = Integer.parseInt(line.split(" ")[0]);
				line = in.nextLine();
				telf = line.split(" ")[0];
				line = in.nextLine();
				calif_o_salario = Double.parseDouble(line.split(" ")[0]);
				Employee emp = new Employee(dni,nombre,edad,telf,calif_o_salario);
				t1.insert(emp);
			}
		}
		System.out.println( "\nOrdenados por NOMBRE: " );
		t1.printInOrder();


		// codigo para probar algunos metodos de BinarySearchTree
		BinarySearchTree<Integer> t2 = new BinarySearchTree<>();
		t2.insert(6);
		t2.insert(4);
		t2.insert(2);
		t2.insert(5);
		t2.insert(10);
		t2.insert(7);;
		t2.insert(11);
		t2.insert(13);
		t2.printPreOrder();
		int xx = 11;
		System.out.println( "num nodos arbol = "+t2.num_nodos()+"\nnumero de hojas = "+t2.num_hojas()+
				"\nmenores que "+xx+" = "+t2.menoresQue(xx));

	}
}

class OrderPersonByName implements Comparator<Person>
{
	public int compare( Person p1, Person p2 )
	{   // un comparador por nombre
		return p1.getName().compareTo(p2.getName());
	}
}


class Person implements Comparable<Person>
{
	public Person( String dni, String n, int ag, String p )
	{
		this.dni = dni; name = n; age = ag;  phone = p;
	}

	public String toString( )
	{
		return getDni( ) + " " +getName( ) + " " + getAge( ) + " " + getPhoneNumber( );
	}

	public int compareTo( Person rhs ) // comparar por DNI
	{
		return dni.compareTo(rhs.dni);
	}

	public final String getDni( )
	{
		return dni;
	}

	public final String getName( )
	{
		return name;
	}

	public final int getAge( )
	{
		return age;
	}

	public final String getPhoneNumber( )
	{
		return phone;
	}

	public final void setPhoneNumber( String newPhone )
	{
		phone = newPhone;
	}

	private String dni;
	private String name;
	private int age;
	private String phone;

}

class Student extends Person
{
	public Student( String dni, String n, int ag, String p, double promedio )
	{
		super( dni, n, ag,  p );
		this.promedio = promedio;
	}

	public String toString( )
	{
		return super.toString( ) + " Promedio= " + getPromedio();
	}

	public double getPromedio( )
	{
		return promedio;
	}

	private double promedio;
}

class Employee extends Person
{
	public Employee( String dni, String n, int ag,  String p, double salario )
	{
		super( dni, n, ag,  p );
		this.salario = salario;
	}

	public String toString( )
	{
		return super.toString( ) + " $" + getSalario( );
	}

	public double getSalario( )
	{
		return salario;
	}

	public void raise( double percentRaise )
	{
		salario *= ( 1 + percentRaise );
	}

	private double salario;
}

/// codigo alternativo para Leer de un archivo de texto

////Leer archivo e ir guardando a los estudiantes 
//// y empleados en el Ã¡rbol

//BufferedReader reader = new BufferedReader(new InputStreamReader(
//	      new FileInputStream("archivo.txt"), "UTF-8"));
//
//line = reader.readLine();
//while (line!=null) {
//	 
//	 System.out.println(line);
//	 
//	 tipo = line.split(" ")[0];
//	 System.out.println(tipo+(tipo.equals("S")));
//	 if (tipo.equals("S")) { 
//		 line = reader.readLine();
// 	 dni = line.split(" ")[0];
//		 nombre = reader.readLine();
//		 line = reader.readLine();
// 	 edad = Integer.parseInt(line.split(" ")[0]);
// 	 line = reader.readLine();
// 	 telf = line.split(" ")[0];
// 	 line = reader.readLine();
// 	 calif_o_salario = Double.parseDouble(line.split(" ")[0]);
// 	 Student est = new Student(dni,nombre,edad,telf,
// 			 calif_o_salario);
// 	 System.out.println("estudiante "+dni+" "+nombre+" "+
// 	                    edad+" "+telf+" "+calif_o_salario);
// 	 t.insert(est);
// 	 
//	 }
//	 if (tipo.equals("E")) {
//		 line = reader.readLine();
// 	 dni = line.split(" ")[0];
//		 nombre = reader.readLine();
//		 line = reader.readLine();
// 	 edad = Integer.parseInt(line.split(" ")[0]);
// 	 line = reader.readLine();
// 	 telf = line.split(" ")[0];
// 	 line = reader.readLine();
// 	 calif_o_salario = Double.parseDouble(line.split(" ")[0]);
// 	 Employee emp = new Employee(dni,nombre,edad,telf,
// 			 calif_o_salario);
// 	 System.out.println("empleado "+dni+" "+nombre+" "+
//                 edad+" "+telf+" "+calif_o_salario);
// 	 t.insert(emp);
//	 }
//	 line = reader.readLine();
//}
//reader.close();