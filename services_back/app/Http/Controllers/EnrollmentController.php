<?php

namespace App\Http\Controllers;

use App\Models\Enrollment;
use Illuminate\Http\Request;

class EnrollmentController extends Controller
{

    public function index(Request $request)
    {
        $query = Enrollment::with(['student', 'course']);

        if ($request->filled('student_id')) {
            $query->where('student_id', $request->student_id);
        }

        if ($request->filled('course_id')) {
            $query->where('course_id', $request->course_id);
        }

        $enrollments = $query->get();

        return response()->json($enrollments);
    }  

    public function save(Request $request)
    {
        $request->validate([
            'student_id' => 'required|exists:students,id',
            'course_id' => 'required|exists:courses,id',
        ]);

        $exists = Enrollment::where('student_id', $request->student_id)
                           ->where('course_id', $request->course_id)
                           ->first();
        
        if ($exists) {
            return response()->json(['error' => 'Ya existe'], 404);
        }

        $saved = Enrollment::create([
            "student_id" => $request->input('student_id'),
            "course_id" => $request->input('course_id'),
            "grade" => $request->input('grade')
        ]);

        return response()->json($saved);
    }

    public function show($id)
    {
        return response()->json(Enrollment::with(["student", "course"])->find($id));
    }

    public function update(Request $request, $id)
    {
        $enrollment = Enrollment::find($id);
        if (!$enrollment) {
            return response()->json(['error' => 'No encontrado'], 404);
        }

        $enrollment->student_id = $request->input('student_id');
        $enrollment->course_id  = $request->input('course_id');
        $enrollment->grade      = $request->input('grade');
        $enrollment->save();

        $enrollment->load(['student', 'course']);
        return response()->json($enrollment);
    }

    public function delete($id)
    {
        $enrollment = Enrollment::find($id);
        if (!$enrollment) {
            return response()->json(['error' => 'No encontrado'], 404);
        }

        $enrollment->delete();
        return response()->json(['message' => 'Eliminado correctamente']);
    }
}
