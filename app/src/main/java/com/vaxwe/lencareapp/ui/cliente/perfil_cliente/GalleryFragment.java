package com.vaxwe.lencareapp.ui.cliente.perfil_cliente;

import static android.app.Activity.RESULT_OK;
import static com.google.firebase.database.FirebaseDatabase.getInstance;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
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

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.vaxwe.lencareapp.MainActivityCliente;
import com.vaxwe.lencareapp.R;
import com.vaxwe.lencareapp.databinding.FragmentGalleryBinding;
import com.vaxwe.lencareapp.ui.cliente.Cambiar_Pass_C;

import java.util.HashMap;

public class GalleryFragment extends Fragment {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference BaseDatos;
    StorageReference storageReference;
    String RutaAlmacenamiento = "Foto_Perfil_Cliente/*";


    private Uri imagen_uri;
    private String imagen_perfil;
    private ProgressDialog progressDialog;

    ImageView ImagenCliente;
    TextView UidCliente, NombreCliente,NombreCliente2,ApellidoCliente,ApellidoCliente2,EmailCliente,PassdordCliente;
    Button PasswordActualizarCliente,DatosActualizarCliente;


    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        ImagenCliente = root.findViewById(R.id.ImagenCliente);
        UidCliente = root.findViewById(R.id.UidCliente);
        NombreCliente = root.findViewById(R.id.NombreCliente);
        NombreCliente2 = root.findViewById(R.id.NombreCliente2);
        ApellidoCliente = root.findViewById(R.id.ApellidoCliente);
        ApellidoCliente2 = root.findViewById(R.id.ApellidoCliente2);
        EmailCliente = root.findViewById(R.id.EmailCliente);
        PassdordCliente = root.findViewById(R.id.PassdordCliente);
        PasswordActualizarCliente = root.findViewById(R.id.PasswordActualizarCliente);
        DatosActualizarCliente = root.findViewById(R.id.DatosActualizarCliente);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();

        progressDialog = new ProgressDialog(getActivity());


        BaseDatos = getInstance().getReference("DBAdmin");

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

                    UidCliente.setText(uid);
                    NombreCliente.setText(nombre);
                    NombreCliente2.setText(nombre);
                    ApellidoCliente.setText(apellido);
                    ApellidoCliente2.setText(apellido);
                    EmailCliente.setText(correo);
                    PassdordCliente.setText(password);

                    try {//SI EXISTE LA IMAGEN
                        Picasso.get().load(imagen).placeholder(R.drawable.perfil_1).into(ImagenCliente);

                    }catch (Exception e){// NO EXTISTE LA IMAGEN
                        Picasso.get().load(R.drawable.perfil_1).into(ImagenCliente);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ImagenCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CambiarImagenPerfilCliente();
            }
        });

        PasswordActualizarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Cambiar_Pass_C.class));
                getActivity().finish();

            }
        });

        DatosActualizarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditarDatosCliente();
            }
        });







        return root;
    }



    private void EditarDatosCliente() {
        String [] opciones = {"Editar Nombre","Editar Apellido"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Elegir Opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    //EDITAR NOMBRE
                    EditarNombreCliente();
                }
                else if (i == 1){
                    //EDITAR APELLIDO
                    EditarApellidoCliente();
                }
            }
        });
        builder.create().show();
    }


    private void EditarNombreCliente() {
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

    private void EditarApellidoCliente() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Actualizar Apellido: ");
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

    private void CambiarImagenPerfilCliente() {
        String [] opcion = {"Cambiar foto de perfil"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Elige una opción");
        builder.setItems(opcion, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    imagen_perfil = "IMAGEN";
                    ElegirFotoCliente();
                }
            }
        });

        builder.create().show();

    }

    private void ElegirFotoCliente() {
        String [] opciones = {"Cámara","Galeria"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Seleccionar imagen de: ");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    //SELECCION CAMARA
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)  == PackageManager.PERMISSION_GRANTED){
                        Elegir_de_CamaraCliente();

                    }else {
                        SolicitudPermisoCamaraCliente.launch(Manifest.permission.CAMERA);
                    }


                }else if (i == 1){
                    //SELECCION GALERIA
                    if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE)  == PackageManager.PERMISSION_GRANTED){
                        Elegir_de_GaleriaCliente();

                    }else {
                        SolicitudPermisoGaleriaCliente.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    }

                }
            }
        });
        builder.create().show();
    }

    private void Elegir_de_GaleriaCliente() {
        Intent GaleriaIntent = new Intent(Intent.ACTION_PICK);
        GaleriaIntent.setType("image/*");
        ObtenerImagenGaleriaCliente.launch(GaleriaIntent);
    }

    //METODO PARA ABRIR LA CAMARA
    private void Elegir_de_CamaraCliente() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"FotoTemporal");
        values.put(MediaStore.Images.Media.DESCRIPTION,"Descripción Temporal");
        imagen_uri = (requireActivity()).getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camaraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imagen_uri);
        ObtenerImagenCamaraCliente.launch(camaraIntent);

    }

    private ActivityResultLauncher<Intent> ObtenerImagenCamaraCliente = registerForActivityResult
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

    private ActivityResultLauncher<Intent> ObtenerImagenGaleriaCliente = registerForActivityResult
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


    private ActivityResultLauncher<String> SolicitudPermisoCamaraCliente = registerForActivityResult(new ActivityResultContracts.RequestPermission(),isGranted ->{
        if (isGranted){
            Elegir_de_CamaraCliente();

        }else{
            Toast.makeText(getActivity(), "Permiso denegado", Toast.LENGTH_SHORT).show();
        }
    });

    private ActivityResultLauncher<String> SolicitudPermisoGaleriaCliente = registerForActivityResult(new ActivityResultContracts.RequestPermission(),isGranted ->{
        if (isGranted){
            Elegir_de_GaleriaCliente();

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
                                    startActivity(new Intent(getActivity(), MainActivityCliente.class));
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