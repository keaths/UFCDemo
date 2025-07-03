import Content from "./Content/Content";
import Header from "./Header/Header";
import Reaction from "./Reaction/Reaction";

export const Post: React.FC<{}> = (props) => {
    return (
        <div className="d-flex justify-content-center mb-5">
            <div className="d-flex bg-info rounded-3 mb-5" style={{ width: "75%" }}>
                <div className="w-100 h-100">
                    <Header />
                    <Content />
                    <Reaction />
                </div>
            </div>
        </div>
    )
}
export default Post;