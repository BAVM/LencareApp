package com.vaxwe.lencareapp.ui.admin.perfil_admin;

import static android.app.Activity.RESULT_OK;
import static com.google.firebase.storage.FirebaseStorage.getInstance;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.vaxwe.lencareapp.MainActivityAdmin;
import com.vaxwe.lencareapp.R;
import com.vaxwe.lencareapp.databinding.FragmentPerfilABinding;
import com.vaxwe.lencareapp.ui.admin.Cambiar_Pass;

import java.util.HashMap;


public class PerfilFragmentA extends Fragment {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference BaseDatos;

    StorageReference storageReference;
    String RutaAlmacenamiento = "Foto_Perfil_Admin/*";


    private Uri imagen_uri;
    private String imagen_perfil;
    private ProgressDialog progressDialog;

    ImageView ImagenAdministrador;
    TextView UidAdministrador, NombreAdministrador,NombreAdministrador2,ApellidoAdministrador,ApellidoAdministrador2,EmailAdministrador,PassdordAdministrador;
    Button PasswordActualizarAdministrador,DatosActualizarAdministrador;




    private FragmentPerfilABinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        PerfilViewModel perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilABinding.inflate(inflater,container,false);
        View root = binding.getRoot();


        ImagenAdministrador = root.findViewById(R.id.ImagenAdministrador);
        UidAdministrador = root.findViewById(R.id.UidAdministrador);
        NombreAdministrador = root.findViewById(R.id.NombreAdministrador);
        NombreAdministrador2 = root.findViewById(R.id.NombreAdministrador2);
        ApellidoAdministrador = root.findViewById(R.id.ApellidoAdministrador);
        ApellidoAdministrador2 = root.findViewById(R.id.ApellidoAdministrador2);
        EmailAdministrador = root.findViewById(R.id.EmailAdministrador);
        PassdordAdministrador = root.findViewById(R.id.PassdordAdministrador);
        PasswordActualizarAdministrador = root.findViewById(R.id.PasswordActualizarAdministrador);
        DatosActualizarAdministrador = root.findViewById(R.id.DatosActualizarAdministrador);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        storageReference = getInstance().getReference();

        progressDialog = new ProgressDialog(getActivity());


        BaseDatos = FirebaseDatabase.getInstance().getReference("DBAdmin");

        BaseDatos.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String uid = ""+snapshot.child("UID").getValue();
                    String nombre = ""+snapshot.child("NOMBRE").getValue();
                    String apellido = ""+snapshot.child("APELLIDO").getValue();
                    String correo = ""+snapshot.child("EMAIL").getValue();
                    String password = ""+snapshot.child("PASSWORD").getValue();
                    String imagen = ""+snapshot.child("IMAGEN").getValue();

                    UidAdministrador.setText(uid);
                    NombreAdministrador.setText(nombre);
                    NombreAdministrador2.setText(nombre);
                    ApellidoAdministrador.setText(apellido);
                    ApellidoAdministrador2.setText(apellido);
                    EmailAdministrador.setText(correo);
                    PassdordAdministrador.setText(password);

                    try {//SI EXISTE LA IMAGEN
                        Picasso.get().load(imagen).placeholder(R.drawable.perfil_1).into(ImagenAdministrador);

                    }catch (Exception e){// NO EXTISTE LA IMAGEN
                        Picasso.get().load(R.drawable.perfil_1).into(ImagenAdministrador);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ImagenAdministrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CambiarImagenPerfilAdmin();
            }
        });


        PasswordActualizarAdministrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Cambiar_Pass.class));
                getActivity().finish();

            }
        });

        DatosActualizarAdministrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditarDatos();
            }
        });




        // Inflate the layout for this fragment
        return root;


    }


    private void EditarDatos() {
        String [] opciones = {"Editar Nombre","Editar Apellido"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Elegir Opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    //EDITAR NOMBRE
                    EditarNombre();
                }
                else if (i == 1){
                    //EDITAR APELLIDO
                    EditarApellido();
                }
            }
        });
        builder.create().show();
    }


    private void EditarNombre() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Actualizar nombre: ");
        LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getActivity());
        linearLayoutCompat.setOrientation(LinearLayoutCompat.VERTICAL);
        linearLayoutCompat.setPadding(10,10,10,10);
        EditText editText = new EditText(getActivity());
        editText.setHint("Ingrese el nombre...");
        editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS|InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
        linearLayoutCompat.addView(editText);
        builder.setView(linearLayoutCompat);
        builder.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String nuevoDato = editText.getText().toString().trim();
                if (!nuevoDato.equals("")){
                    HashMap<String,Object> resultado = new HashMap<>();
                    resultado.put("NOMBRE",nuevoDato);
                    BaseDatos.child(user.getUid()).updateChildren(resultado).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getActivity(), "Nombre Actualizado", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {
                    Toast.makeText(getActivity(), "Campo vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "Cancelado", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();

    }

    private void EditarApellido() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Actualizar apellido: ");
        LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getActivity());
        linearLayoutCompat.setOrientation(LinearLayoutCompat.VERTICAL);
        linearLayoutCompat.setPadding(10,10,10,10);
        EditText editText = new EditText(getActivity());
        editText.setHint("Ingrese el apellido...");
        editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS|InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
        linearLayoutCompat.addView(editText);
        builder.setView(linearLayoutCompat);
        builder.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String nuevoDato = editText.getText().toString().trim();
                if (!nuevoDato.equals("")){
                    HashMap<String,Object> resultado = new HashMap<>();
                    resultado.put("APELLIDO",nuevoDato);
                    BaseDatos.child(user.getUid()).updateChildren(resultado).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getActivity(), "Apellido Actualizado", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {
                    Toast.makeText(getActivity(), "Campo vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "Cancelado", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();

    }


    private void CambiarImagenPerfilAdmin() {
        String [] opcion = {"Cambiar foto de perfil"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Elige una opción");
        builder.setItems(opcion, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    imagen_perfil = "IMAGEN";
                    ElegirFoto();
                }
            }
        });

        builder.create().show();

    }

    private void ElegirFoto() {
        String [] opciones = {"Cámara","Galeria"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Seleccionar imagen de: ");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    //SELECCION CAMARA
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)  == PackageManager.PERMISSION_GRANTED){
                        Elegir_de_Camara();

                    }else {
                        SolicitudPermisoCamara.launch(Manifest.permission.CAMERA);
                    }


                }else if (i == 1){
                    //SELECCION GALERIA
                    if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE)  == PackageManager.PERMISSION_GRANTED){
                        Elegir_de_Galeria();

                    }else {
                        SolicitudPermisoGaleria.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    }

                }
            }
        });
        builder.create().show();
    }

    private void Elegir_de_Galeria() {
        Intent GaleriaIntent = new Intent(Intent.ACTION_PICK);
        GaleriaIntent.setType("image/*");
        ObtenerImagenGaleria.launch(GaleriaIntent);
    }

    //METODO PARA ABRIR LA CAMARA
    private void Elegir_de_Camara() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"FotoTemporal");
        values.put(MediaStore.Images.Media.DESCRIPTION,"Descripción Temporal");
        imagen_uri = (requireActivity()).getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camaraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imagen_uri);
        ObtenerImagenCamara.launch(camaraIntent);

    }

    private ActivityResultLauncher<Intent> ObtenerImagenCamara = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK){
                        ActualizarImagen_BD(imagen_uri);
                        progressDialog.setTitle("Cargando...");
                        progressDialog.setMessage("Cambiando la imagen, por favor espere.");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                    }else {
                        Toast.makeText(getActivity(), "Proceso cancelado", Toast.LENGTH_SHORT).show();
                    }

                }
            });

    private ActivityResultLauncher<Intent> ObtenerImagenGaleria = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK){
                        Intent data = result.getData();
                        imagen_uri = data.getData();
                        ActualizarImagen_BD(imagen_uri);
                        progressDialog.setTitle("Cargando...");
                        progressDialog.setMessage("Cambiando la imagen, por favor espere.");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                    }else {
                        Toast.makeText(getActivity(), "Proceso cancelado", Toast.LENGTH_SHORT).show();
                    }

                }
            });


    private ActivityResultLauncher<String> SolicitudPermisoCamara = registerForActivityResult(new ActivityResultContracts.RequestPermission(),isGranted ->{
        if (isGranted){
            Elegir_de_Camara();

        }else{
            Toast.makeText(getActivity(), "Permiso denegado", Toast.LENGTH_SHORT).show();
        }
    });


    private ActivityResultLauncher<String> SolicitudPermisoGaleria = registerForActivityResult(new ActivityResultContracts.RequestPermission(),isGranted ->{
        if (isGranted){
            Elegir_de_Galeria();

        }else{
            Toast.makeText(getActivity(), "Permiso denegado", Toast.LENGTH_SHORT).show();
        }
    });


    private void ActualizarImagen_BD(Uri uri){
        String Ruta_Archvo_Nombre = RutaAlmacenamiento + "" + imagen_perfil + "_" + user.getUid();
        StorageReference storageReference1 = storageReference.child(Ruta_Archvo_Nombre);
        storageReference1.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());
                Uri downloadUri = uriTask.getResult();
                if (uriTask.isSuccessful()){
                    HashMap<String,Object> result = new HashMap<>();
                    result.put(imagen_perfil,downloadUri.toString());
                    BaseDatos.child(user.getUid()).updateChildren(result).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    startActivity(new Intent(getActivity(), MainActivityAdmin.class));
                                    getActivity().finish();
                                    Toast.makeText(getActivity(), "Imagen Actualizada", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                }else{
                    Toast.makeText(getActivity(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }









    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}