package ni.edu.uca.sistematicopersistencia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.room.CoroutinesRoom
import androidx.room.Room
import kotlinx.coroutines.launch
import ni.edu.uca.sistematicopersistencia.data.database.BaseDatos
import ni.edu.uca.sistematicopersistencia.data.database.dao.ProductoDao
import ni.edu.uca.sistematicopersistencia.data.database.entities.EntityProducto
import ni.edu.uca.sistematicopersistencia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var  room: BaseDatos
    lateinit var producto: EntityProducto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        room = Room.databaseBuilder(this, BaseDatos::class.java, "BaseDatos").build()
        binding.btnGuardar.setOnClickListener {
            producto = EntityProducto(
                0,
                 binding.etNombre.text.toString().trim(),
                binding.etPrecio.text.toString().toDouble(),
                binding.etExistencia.text.toString().toInt()
            )
            agregarProd(room, producto)
        }
    }
    fun agregarProd(room: BaseDatos, producto:EntityProducto){
        lifecycleScope.launch {
            room.productoDao().insertarReg(producto)
        }
    }
}