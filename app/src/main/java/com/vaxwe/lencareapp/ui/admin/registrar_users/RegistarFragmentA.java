package com.vaxwe.lencareapp.ui.admin.registrar_users;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vaxwe.lencareapp.MainActivityAdmin;
import com.vaxwe.lencareapp.R;
import com.vaxwe.lencareapp.databinding.FragmentRegistarABinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public class RegistarFragmentA extends Fragment {

    TextView Fecha;
    EditText Nombre,Apellido,Email,Password;
    Spinner Roll;
    Button Registrar;

    FirebaseAuth auth;
    ProgressDialog progressDialog;
    private FragmentRegistarABinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        RegistrarViewModel registrarViewModel = new ViewModelProvider(this).get(RegistrarViewModel.class);
        binding = FragmentRegistarABinding.inflate(inflater,container,false);
        View root = binding.getRoot();


        Fecha = root.findViewById(R.id.Fecha);
        Nombre = root.findViewById(R.id.Nombre);
        Apellido = root.findViewById(R.id.Apellido);
        Email = root.findViewById(R.id.Email);
        Password = root.findViewById(R.id.Password);
        Roll = root.findViewById(R.id.Roll);

        Registrar = root.findViewById(R.id.Registrar);

        auth = FirebaseAuth.getInstance();//INICIALIZAMOS FIREBASE AUTENTICACION

        Date date = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("d 'de' MMM 'del' yyyy");
        String Sfecha = fecha.format(date); // SE CONVIERTE LA FECHA A STRING
        Fecha.setText(Sfecha);

        //ARRAY SELECCION PAIS
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.Roll, android.R.layout.simple_spinner_item);
        Roll.setAdapter(adapter1);

        //AL HACER CLIC EN REGISTRAR
        Registrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //CONVERTIR A STRING
                String nombre = Nombre.getText().toString();
                String apellido = Apellido.getText().toString();
                String email = Email.getText().toString();
                String pass = Password.getText().toString();
                String roll = Roll.getSelectedItem().toString();

                if (nombre.equals("") || apellido.equals("") || email.equals("") || pass.equals("") || roll.equals("")){
                    Toast.makeText(getActivity(), "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();

                }else{
                    //VALIDAMOS EMAIL
                    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        Email.setError("Correo invalido");
                        Email.setFocusable(true);

                    }else if(pass.length()<6){
                        Password.setError("La contaseña debe ser igual o mayor a 6 caracteres");
                        Password.setFocusable(true);

                    }
                    else {
                        RAdmin(email,pass);
                    }

                }


            }
        });

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Registrando nuevo usuario");
        progressDialog.setCancelable(false);



        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    //METODO PARA REGISTRAR ADMINISTRADORES
    private void RAdmin(String email, String pass) {
        progressDialog.show();
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //SI EL ADMINISTRADOR FUE CREADO CORRECTAMENTE
                if (task.isSuccessful()){

                    progressDialog.dismiss();
                    FirebaseUser user =auth.getCurrentUser();
                    assert user != null; //AFIRMAR QUE EL ADMIN NO ES NULO

                    //CONVERTIR A CADENA LOS DATOS DE ADMINISTRADORES
                    String UID = user.getUid();
                    String nombre = Nombre.getText().toString();
                    String apellido = Apellido.getText().toString();
                    String email = Email.getText().toString();
                    String pass = Password.getText().toString();
                    String roll = Roll.getSelectedItem().toString();
                    //CONVERTIR ENTERO A STRING
                    //int valorx = Integer.parseInt(valor)

                    HashMap<Object,Object> Administradores = new HashMap<>();
                    Administradores.put("UID",UID);
                    Administradores.put("NOMBRE",nombre);
                    Administradores.put("APELLIDO",apellido);
                    Administradores.put("EMAIL",email);
                    Administradores.put("PASSWORD",pass);
                    Administradores.put("ROLL",roll);
                    Administradores.put("IMAGEN","");

                    //INICIALIZAMOS FIREBASE DATABASE
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference("DBAdmin");
                    reference.child(UID).setValue(Administradores);
                    startActivity(new Intent(getActivity(), MainActivityAdmin.class));
                    Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                    getActivity().finish();


                }else{
                    if (task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getActivity(), "El usuario ya existe.", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }



}