import { timeEnd } from "console";

export const Scheds = () => {
    return (
        <div className="d-flex bg-success justify-content-start border border border-black p-0 w-100">
            <div className="d-inline-flex w-100 p-1">
                <div className="d-flex h-100 bg-info w-100">
                    <div className="container-fluid p-0 w-100 h-100">
                        <div className="bg-primary w-25 rounded-end-4" style={{height:"calc(100%/5)"}}></div>
                        <div className="bg-primary w-50 rounded-end-4" style={{height:"calc(100%/5)"}}></div>
                        <div className="bg-primary w-25 rounded-end-4" style={{height:"calc(100%/5)"}}></div>
                        <div className="bg-primary w-75 rounded-end-4" style={{height:"calc(100%/5)"}}></div>
                        <div className="bg-primary w-75 rounded-end-4" style={{height:"calc(100%/5)"}}></div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Scheds;