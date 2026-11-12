
export default async function getThumbnail(req, res){
    const path = req.params;

    if(path.endsWith(".mp4")){
        return res.status(400).json({error: "Not a video file"});
    }

    
}