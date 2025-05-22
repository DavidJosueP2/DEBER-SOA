<?php

namespace App\Http\Controllers;

use App\Models\Course;
use Illuminate\Http\Request;

class CourseController extends Controller
{
    public function index()
    {
        return response()->json(Course::get());
    }

    public function save(Request $request)
    {
        $saved = Course::create([
            "title" => $request->input('title'),
            "description" => $request->input('description')
        ]);

        return response()->json($saved);
    }

    public function show($id)
    {
        $course = Course::find($id);
        if (!$course) {
            return response()->json(['error' => 'No encontrado'], 404);
        }

        return response()->json($course);
    }

    public function update(Request $request, $id)
    {
        $course = Course::find($id);
        if (!$course) {
            return response()->json(['error' => 'No encontrado'], 404);
        }

        $course->title       = $request->input('title');
        $course->description = $request->input('description');
        $course->save();

        return response()->json($course);
    }

    public function delete($id)
    {
        $course = Course::find($id);
        if (!$course) {
            return response()->json(['error' => 'No encontrado'], 404);
        }

        $course->delete();
        return response()->json(['message' => 'Eliminado correctamente']);
    }

    public function withEnrollments($id)
    {

        $course = Course::with(["enrollments"])->find($id);
        if (!$course) {
            return response()->json(['error' => 'No encontrado'], 404);
        }

        return response()->json($course);
    }
}
